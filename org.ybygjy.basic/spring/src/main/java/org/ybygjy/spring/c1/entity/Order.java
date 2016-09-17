package org.ybygjy.spring.c1.entity;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Order {
    private User user;
    private int orderNo;
    private int orderAmount;
    private Object buyerObj;
    private Properties vendorLists;
    private List<String> expressPath;
    private Map<String, String> extAttr;
    private Set<String> promotions;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public int getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
    public int getOrderAmount() {
        return orderAmount;
    }
    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }
    
    public Object getBuyerObj() {
        return buyerObj;
    }
    public void setBuyerObj(Object buyerObj) {
        this.buyerObj = buyerObj;
    }
    public Properties getVendorLists() {
        return vendorLists;
    }
    public void setVendorLists(Properties vendorLists) {
        this.vendorLists = vendorLists;
    }
    public List<String> getExpressPath() {
        return expressPath;
    }
    public void setExpressPath(List<String> expressPath) {
        this.expressPath = expressPath;
    }
    public Map<String, String> getExtAttr() {
        return extAttr;
    }
    public void setExtAttr(Map<String, String> extAttr) {
        this.extAttr = extAttr;
    }
    public Set<String> getPromotions() {
        return promotions;
    }
    public void setPromotions(Set<String> promotions) {
        this.promotions = promotions;
    }
    @Override
    public String toString() {
        return "Order [user=" + user.getUserNo() + ", orderNo=" + orderNo + ", orderAmount=" + orderAmount + ", buyerObj=" + buyerObj
                + ", vendorLists=" + vendorLists + ", expressPath=" + expressPath + ", extAttr=" + extAttr + ", promotions=" + promotions
                + "]";
    }
}
