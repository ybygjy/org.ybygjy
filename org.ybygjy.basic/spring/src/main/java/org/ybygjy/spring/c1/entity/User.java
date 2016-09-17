package org.ybygjy.spring.c1.entity;

import java.util.Arrays;

/**
 * User
 * @author WangYanCheng
 * @version 2016年9月15日
 */
public class User {
    private int userNo;
    private String userName;
    private String address;
    private String[] phoneNumbers;
    private Order order;
    public User() {
    }
    public User(int userNo, String userName) {
        super();
        this.userNo = userNo;
        this.userName = userName;
    }
    public User(int userNo, String userName, String address) {
        this.userNo = userNo;
        this.userName = userName;
        this.address = address;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setPhoneNumbers(String[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    @Override
    public String toString() {
        return "User [userNo=" + userNo + ", userName=" + userName + ", address=" + address + ", phoneNumbers="
                + Arrays.toString(phoneNumbers) + ", order=" + order + "]";
    }
}
