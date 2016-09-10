package org.ybygjy.pay.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 抽象交易响应实体
 * @author WangYanCheng
 * @version 2016年8月3日
 */
public class AbstractPayRepDTO {
    /** 错误码*/
    private String errorCode;
    /** 错误信息*/
    private String errorMessage;
    /** 响应时间 */
    private Date rtnDate;
    /** 响应内容 */
    private String rtnContent;
    /** 响应状态 */
    private TradeStatusEnum rtnStatus;
    /** 扩展内容 */
    private Map<String, String> extData = new HashMap<String, String>();

    public Date getRtnDate() {
        return rtnDate;
    }

    public void setRtnDate(Date rtnDate) {
        this.rtnDate = rtnDate;
    }

    public String getRtnContent() {
        return rtnContent;
    }

    public void setRtnContent(String rtnContent) {
        this.rtnContent = rtnContent;
    }

    public TradeStatusEnum getRtnStatus() {
        return rtnStatus;
    }

    public void setRtnStatus(TradeStatusEnum rtnStatus) {
        this.rtnStatus = rtnStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<String, String> getExtData() {
        return extData;
    }

    public void setExtData(Map<String, String> extData) {
        this.extData = extData;
    }
}
