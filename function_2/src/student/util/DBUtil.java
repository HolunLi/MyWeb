package student.util;

import java.sql.*;

//数据库帮助类(通用的操作)
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false&serverTimezone=GMT%2B8";
    private static final String USER = "root";
    private static final String PWD = "haolun";
    public static Connection connection = null;
    public static PreparedStatement pStatement = null;
    public static ResultSet resultSet = null;

    //连接数据库
    public static void connectDatabase() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PWD);
    }

    //关闭所有的资源
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取pStatement对象
    public static void createPreparedStatement(String sql, Object... args) throws SQLException {
        pStatement = connection.prepareStatement(sql);
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                pStatement.setObject(i+1, args[i]);
            }
        }
    }

    //update方法适用于大部分的增删改操作
    public static int update(String sql, Object... args) { //ars是一个Object类型的可变长参数,本质上就是一个Object数组,等价于Object[] args
        try {
            connectDatabase();
            createPreparedStatement(sql, args);
            int count = pStatement.executeUpdate();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        } finally {
            close(connection, pStatement, null);
        }
    }

    //query方法适用于的大部分的查询操作
    public static ResultSet query(String sql, Object... args) { //ars是一个Object类型的可变长参数,本质上就是一个Object数组,等价于Object[] args
        try {
            connectDatabase();
            createPreparedStatement(sql, args);
            resultSet = pStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //getTotalCount方法适用于查询一个表中总共包含几条数据,即总数据数(select count(*) from 表名)
    public static int getTotalCount(String sql) {
        int count = -1;
        try {
            connectDatabase();
            createPreparedStatement(sql, null);
            resultSet = pStatement.executeQuery();
            if (resultSet.next())
                count = resultSet.getInt(1);
            return count;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }finally {
            close(connection, pStatement, resultSet);
        }
    }
}
