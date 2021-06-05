package util;

import org.apache.commons.dbcp.BasicDataSource;

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

    //tomcat-dbcp
    /*public static void connectDatabase() {
        *//*
        直接与数据库建立连接(不推荐)
        Class.forName("com.mysql.cj.jdbc.Driver");
          connection = DriverManager.getConnection(URL, USER, PWD); *//*

        try {

            //初始化查找命名空间
            Context context = context = new InitialContext();
            //获取DataSource。根据数据源名称进行定位,java:comp/env是必须加的,后面跟你的DataSource名。
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/student");
            connection = dataSource.getConnection(); //从连接池中获取数据库连接(connection对象)

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    //dbcp
    public static void connectDatabase() {
        //创建数据源basicDataSource
        BasicDataSource basicDataSource = new BasicDataSource();

        //设置数据库驱动类名
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //设置连接数据库的URL
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/mydatabase?useSSL=false&serverTimezone=GMT%2B8");
        //设置连接数据库的的用户名
        basicDataSource.setUsername("root");
        //设置连接数据库的密码
        basicDataSource.setPassword("haolun");
        //设置初始化,连接池中的"数据库连接"的数量
        basicDataSource.setInitialSize(15);
        //设置连接池中,处于活动状态的数据库连接的最大数量
        basicDataSource.setMaxActive(10);
        //设置连接池中,处于空闲状态的数据库连接的最大数量
        basicDataSource.setMaxIdle(3);

        try {
            connection = basicDataSource.getConnection(); //从连接池中获取数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        connectDatabase();
        try {
            createPreparedStatement(sql, args);
            resultSet = pStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
