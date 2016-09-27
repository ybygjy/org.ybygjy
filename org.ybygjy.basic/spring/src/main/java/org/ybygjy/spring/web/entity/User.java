package org.ybygjy.spring.web.entity;

import java.util.Arrays;

/**
 * UserEntity
 * @author WangYanCheng
 * @version 2016年9月27日
 */
public class User {
    private String no;
    private String name;
    private int age;
    private String[] addresses;
    public User(String no, String name, int age) {
        this.no = no;
        this.name = name;
        this.age = age;
    }
    /**
     * 无参构造方法
     */
    public User() {
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String[] getAddresses() {
        return addresses;
    }
    public void setAddresses(String[] addresses) {
        this.addresses = addresses;
    }
    @Override
    public String toString() {
        return "User [no=" + no + ", name=" + name + ", age=" + age + ", addresses=" + Arrays.toString(addresses) + "]";
    }
}
