package service;

import dao.UserDao;
import entity.User;

public class UserService {
    private UserDao userDao = new UserDao();

    //判断用户是否存在
    public boolean isExist(User user) {
        return userDao.isExist(user);
    }
}
