package org.ybygjy.spring.service;

import org.ybygjy.spring.dao.UserDao;

/**
 * @author WangYanCheng
 * @version 2016年9月15日
 */
public interface UserService {
    public void getUser();
    public void setUserDao(UserDao userDao);
}
