package org.ybygjy.spring.c1.dao.impl;

import org.ybygjy.spring.c1.dao.UserDao;
import org.ybygjy.spring.c1.entity.User;

public class UserDaoImpl4MySql implements UserDao {
    private User user;
    public UserDaoImpl4MySql(User user) {
        this.user = user;
    }
    @Override
    public User getUser(int userNo) {
        return user;
    }

    @Override
    public int insertUser(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteUser(User user) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateUser(User user) {
        // TODO Auto-generated method stub
        return 0;
    }
}
