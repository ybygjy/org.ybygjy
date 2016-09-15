package org.ybygjy.spring.service.impl;

import org.ybygjy.spring.dao.UserDao;
import org.ybygjy.spring.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    @Override
    public void getUser() {
        this.userDao.getUser();
    }
    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
