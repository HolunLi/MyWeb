package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false&serverTimezone=GMT%2B8";
    private static final String USER = "root";
    private static final String PWD = "haolun";
    private static Connection connection = null;
    private static PreparedStatement pStatement = null;
    private static ResultSet resultSet = null;

    //从数据连接池中拿连接(通用)
    public static void connectDatabase() throws ClassNotFoundException, NamingException, SQLException {
        /*
        直接与数据库建立连接(不推荐)
        Class.forName("com.mysql.cj.jdbc.Driver");
          connection = DriverManager.getConnection(URL, USER, PWD); */

        //初始化查找命名空间
        Context context = new InitialContext();
        //获取DataSource。根据数据源名称进行定位,java:comp/env是必须加的,后面跟你的DataSource名。
        DataSource dataSource = (DataSource) context.lookup("java:comp/env/student");
        connection = dataSource.getConnection(); //从数据源中拿数据库连接对象(connection对象)
        System.out.println("hah");
    }

    //关闭所有的资源(通用)
    public static void close() {
        try {
            //注意:从数据源中拿connection对象时，connection.close()不再是关闭与本地数据库的连接，而是将connection对象重新放回到数据源中(可重复使用)
            if (connection != null) connection.close();
            if (pStatement != null) pStatement.close();
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取PreparedStatement对象(通用)
    public static void createPreparedStatement(String sql, String... args) throws SQLException {
            pStatement = connection.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    pStatement.setObject(i+1, args[i]);
                }
            }
    }

    //查询操作(通用)
    public static ResultSet query(String sql, String... args) {
        try {
            connectDatabase();
            createPreparedStatement(sql, args);
            resultSet = pStatement.executeQuery();
            return resultSet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
