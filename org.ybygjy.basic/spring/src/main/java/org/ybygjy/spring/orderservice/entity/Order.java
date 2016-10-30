package org.ybygjy.spring.orderservice.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Order {
    private long id;
    private String orderNo;
    private double orderAmount;
    private int orderFlag;
    private String buyerName;
    private long buyerId;
    private long salesId;
    private String salesName;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date sendTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderMtime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
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
    
    public String getBuyerName() {
        return buyerName;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public String getSalesName() {
        return salesName;
    }
    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }
    @Override
    public String toString() {
        return "Order [id=" + id + ", orderNo=" + orderNo + ", orderAmount=" + orderAmount + ", orderFlag=" + orderFlag + ", buyerName="
                + buyerName + ", buyerId=" + buyerId + ", salesId=" + salesId + ", salesName=" + salesName + ", sendTime=" + sendTime
                + ", orderMtime=" + orderMtime + ", orderCtime=" + orderCtime + ", orderRemark=" + orderRemark + "]";
    }
}
