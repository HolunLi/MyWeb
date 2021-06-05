package util;

import java.sql.*;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false&serverTimezone=GMT%2B8";
    private static final String USER = "root";
    private static final String PWD = "haolun";
    private static Connection connection = null;
    private static PreparedStatement pStatement = null;
    private static ResultSet resultSet = null;

    //连接数据库(通用)
    public static void connectDatabase() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PWD);
    }

    //关闭所有的资源(通用)
    public static void close() {
        try {
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
        }
    }
}
