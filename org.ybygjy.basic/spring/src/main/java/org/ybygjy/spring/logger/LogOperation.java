package org.ybygjy.spring.logger;

import java.io.Serializable;

/**
 * 封装日志操作
 * Created by leye on 2017/11/22.
 */
public class LogOperation implements Serializable {
    private static final long serialVersionUID = 4514083974947478159L;
    /** 操作类型*/
    private Integer operatorType;
    /** 子操作类型*/
    private Integer subOperatorType;
    /** 操作人ID*/
    private String operatorId;
    /** 操作人名称*/
    private String operatorName;
    /** 源系统名称*/
    private String callerAppName;
    /** 源系统调用信息*/
    private String callerLocation;
    /** 操作描述*/
    private String operatorDesc;

    public LogOperation(Integer operatorType, String operatorId, String operatorName) {
        this.operatorType = operatorType;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.callerLocation = LoggerUtils.getInvocationInfo();
        this.callerAppName = LoggerUtils.getApplicationName();
    }

    public LogOperation(Integer operatorType, String operatorId, String operatorName, String callerAppName, String appInvoke) {
        this.operatorType = operatorType;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.callerAppName = callerAppName;
        this.callerLocation = appInvoke;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public Integer getSubOperatorType() {
        return subOperatorType;
    }

    public void setSubOperatorType(Integer subOperatorType) {
        this.subOperatorType = subOperatorType;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getCallerAppName() {
        return callerAppName;
    }

    public void setCallerAppName(String callerAppName) {
        this.callerAppName = callerAppName;
    }

    public String getCallerLocation() {
        return callerLocation;
    }

    public void setCallerLocation(String callerLocation) {
        this.callerLocation = callerLocation;
    }

    @Override
    public String toString() {
        return "LogOperation{" +
                "operatorType=" + operatorType +
                ", subOperatorType=" + subOperatorType +
                ", operatorId='" + operatorId + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", callerAppName='" + callerAppName + '\'' +
                ", callerLocation='" + callerLocation + '\'' +
                ", operatorDesc='" + operatorDesc + '\'' +
                '}';
    }
}
