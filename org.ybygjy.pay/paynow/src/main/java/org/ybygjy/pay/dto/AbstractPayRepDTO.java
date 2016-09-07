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
    /** 响应编码 */
    private int rtnCode;
    /** 响应时间 */
    private Date rtnDate;
    /** 响应内容 */
    private String rtnContent;
    /** 响应状态 */
    private TradeStatusEnum rtnStatus;
    /** 扩展内容 */
    private Map<String, String> extData = new HashMap<String, String>();

    public int getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(int rtnCode) {
        this.rtnCode = rtnCode;
    }

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
        this.rtnCode = rtnStatus.value();
    }

    public Map<String, String> getExtData() {
        return extData;
    }

    public void setExtData(Map<String, String> extData) {
        this.extData = extData;
    }
}
