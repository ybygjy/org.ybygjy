package org.ybygjy.spring.logger;

/**
 * 定义日志操作类型
 * Created by leye on 2017/11/22.
 */
public enum LogOperationType {
    UNKNOWN(999, "未知"),
    SYS_BIZ(10, "系统业务"),
    SYS_TASK(20, "系统调度"),
    MANUAL_OPC(30, "人工"),
    TEST_OPC(40, "测试");

    private Integer typeCode;
    private String typeText;
    private LogOperationType(Integer typeCode, String typeText) {
        this.typeCode = typeCode;
        this.typeText = typeText;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public static LogOperationType resolve(Integer tx) {
        LogOperationType rtnObj = LogOperationType.UNKNOWN;
        for (LogOperationType logOperationType : LogOperationType.values()) {
            if (logOperationType.getTypeCode().equals(tx)) {
                rtnObj = logOperationType;
                break;
            }
        }
        return rtnObj;
    }
}
