package org.ybygjy.spring.c1.service;

import org.ybygjy.spring.c1.entity.User;


/**
 * @author WangYanCheng
 * @version 2016年9月15日
 */
public interface UserService {
    public void createUser(String userName, int userAge);
    public void deleteUser(User user);
    public void motifyUser(User user);
    public User getUser(int userNo);
}
