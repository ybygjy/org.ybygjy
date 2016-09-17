package org.ybygjy.spring.c1.entity;

import java.util.UUID;

/**
 * User实体工厂
 * @author WangYanCheng
 * @version 2016年9月16日
 */
public class UserFactory {
    private static final User userInst = new User();
    static {
        userInst.setAddress(System.clearProperty("user.address"));
        userInst.setUserName(UUID.randomUUID().toString());
        userInst.setUserNo((int) (Math.random() * 10000));
    }
    public UserFactory() {
        System.out.println("UserFactory has been initialized!");
    }
    public static User getUserInstance() {
        return userInst;
    }
    public User buildUser(int userNo, String userName) {
        return new User(userNo, userName);
    }
}
