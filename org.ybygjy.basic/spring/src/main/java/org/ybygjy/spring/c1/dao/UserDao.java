package org.ybygjy.spring.c1.dao;

import org.ybygjy.spring.c1.entity.User;

/**
 * @author WangYanCheng
 * @version 2016年9月15日
 */
public interface UserDao {
    public User getUser(int userNo);
    public int insertUser(User user);
    public int deleteUser(User user);
    public int updateUser(User user);
}
