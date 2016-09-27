package org.ybygjy.spring.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.ybygjy.spring.web.entity.User;

/**
 * userDao
 * @author WangYanCheng
 * @version 2016年9月27日
 */
public class UserDao {
    public boolean insert(User user) {
        System.out.println("Insert user");
        return true;
    }
    public int update(User user) {
        System.out.println("Update users");
        return 1;
    }
    public int delete(User user) {
        System.out.println("Delete users");
        return 1;
    }
    public List<User> getList(User user) {
        List<User> rtnList = new ArrayList<User>();
        rtnList.add(new User("10101", "WangYanCheng00", 28));
        rtnList.add(new User("10102", "WangYanCheng01", 28));
        rtnList.add(new User("10103", "WangYanCheng02", 28));
        return rtnList;
    }
    public User getUserByNo(String no) {
        User user = new User(no, no + ":" + no, 20);
        return user;
    }
}
