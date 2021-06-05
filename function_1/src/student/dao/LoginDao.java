package student.dao;

import student.entity.Login;

import java.sql.*;

public class LoginDao {
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false&serverTimezone=GMT%2B8";
    private static final String USER = "root";
    private static final String PWD = "haolun";

    public static int loginDemo(Login login) {
        Connection connection = null;
        PreparedStatement pst = null;
        ResultSet set = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PWD);
            //这条sql语句只执行结果要么为0,要么为1.如果在login页面输入的用户名和密码在数据库中存在,这条sql语句执行结果为1
            String sql = "select count(*) from login where username=? and userpsw=?";
            pst = connection.prepareStatement(sql);
            pst.setString(1, login.getUsername());
            pst.setString(2, login.getUserpsw());
            set = pst.executeQuery();
            int count = -1;
            if (set.next())
                count = set.getInt(1); //count接受到的值1,说明用户名和密码正确(在数据库中存在)
            return count;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (connection != null && pst != null && set != null) {
                try {
                    set.close();
                    pst.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
