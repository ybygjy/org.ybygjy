package org.ybygjy.spring.c1.service.impl;

import org.ybygjy.spring.c1.aop.LogAnnotation;
import org.ybygjy.spring.c1.dao.UserDao;
import org.ybygjy.spring.c1.entity.User;
import org.ybygjy.spring.c1.service.UserService;

/**
 * UserService
 * @author yancheng.wyc
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public void createUser(String userName, int userAge) {
    }

    @Override
    public void deleteUser(User user) {
    }

    @Override
    public void motifyUser(User user) {
        throw new UnsupportedOperationException("");
    }

    @Override
    @LogAnnotation
    public User getUser(int userNo) {
        return this.userDao.getUser(userNo);
    }
}
