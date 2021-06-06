package util;

import java.sql.*;

public class DBUtil {
    public static Connection connection = null;
    public static PreparedStatement pStatement = null;
    public static ResultSet resultSet = null;

    //从数据库连接池中获取数据库连接(通用)
    public static void connectDatabase() throws SQLException {
        connection = DataSourceUtil.getDataSourceByDbcpProperties().getConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
