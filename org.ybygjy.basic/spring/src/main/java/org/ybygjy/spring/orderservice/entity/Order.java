package org.ybygjy.spring.orderservice.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Order {
    private long id;
    private String orderNo;
    private double orderAmount;
    private int orderFlag;
    private long buyerId;
    private long salesId;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date sendTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderMtime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderCtime;
    private String orderRemark;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public double getOrderAmount() {
        return orderAmount;
    }
    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }
    public int getOrderFlag() {
        return orderFlag;
    }
    public void setOrderFlag(int orderFlag) {
        this.orderFlag = orderFlag;
    }
    public long getBuyerId() {
        return buyerId;
    }
    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }
    public long getSalesId() {
        return salesId;
    }
    public void setSalesId(long salesId) {
        this.salesId = salesId;
    }
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public Date getOrderMtime() {
        return orderMtime;
    }
    public void setOrderMtime(Date orderMtime) {
        this.orderMtime = orderMtime;
    }
    public Date getOrderCtime() {
        return orderCtime;
    }
    public void setOrderCtime(Date orderCtime) {
        this.orderCtime = orderCtime;
    }
    public String getOrderRemark() {
        return orderRemark;
    }
    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }
    @Override
    public String toString() {
        return "Order [id=" + id + ", orderNo=" + orderNo + ", orderAmount=" + orderAmount + ", orderFlag=" + orderFlag + ", buyerId="
                + buyerId + ", salesId=" + salesId + ", sendTime=" + sendTime + ", orderMtime=" + orderMtime + ", orderCtime=" + orderCtime
                + ", orderRemark=" + orderRemark + "]";
    }
}
