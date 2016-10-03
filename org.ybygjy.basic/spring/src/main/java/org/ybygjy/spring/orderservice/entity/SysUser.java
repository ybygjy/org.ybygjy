package org.ybygjy.spring.orderservice.entity;

import java.util.Date;

/**
 * 系统用户
 * @author WangYanCheng
 * @version 2016年10月2日
 */
public class SysUser {
    private long id;
    private String userNo;
    private String userName;
    private String password;
    private String userRole;
    private int stateFlag;
    private Date modifyTime;
    private Date createTime;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserNo() {
        return userNo;
    }
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    public int getStateFlag() {
        return stateFlag;
    }
    public void setStateFlag(int stateFlag) {
        this.stateFlag = stateFlag;
    }
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "SysUser [id=" + id + ", userNo=" + userNo + ", userName=" + userName + ", password=" + password + ", userRole=" + userRole
                + ", stateFlag=" + stateFlag + ", modifyTime=" + modifyTime + ", createTime=" + createTime + "]";
    }
}
