package dao;

import entity.User;
import util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    //判断用户是否存在
    public boolean isExist(User user) {
        return queryUserByNumberAndPassword(user) != null? true : false;
    }

    //根据账号和密码查找对应的用户
    public User queryUserByNumberAndPassword(User user) {
        String sql = "select * from user where userNumber=? and userPwd=?";
        ResultSet rs = DBUtil.query(sql, user.getUserNumber(), user.getUserPwd());
        User aUser = null;
        try {
            if (rs.next()) {
                String number = rs.getString("userNumber");
                String pwd = rs.getString("userPwd");
                aUser = new User(number, pwd);
            }
            return aUser;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            DBUtil.close();
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
