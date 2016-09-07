package org.ybygjy.pay.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 账户余额查询请求实体
 * @author WangYanCheng
 * @version 2016年8月4日
 */
public class AccountBalanceQueryReqDTO extends AbstractPayReqDTO {
    /** 功能码 */
    private String funCode = "AP05";
    /** 商户应用唯一标识码 */
    private String appId;
    /** 商户请求流水号 */
    private String mhtOrderNo;
    /** 商户请求时间 */
    private String mhtReqTime;
    /** 账户类型 */
    private String accountType;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMhtOrderNo() {
        return mhtOrderNo;
    }

    public void setMhtOrderNo(String mhtOrderNo) {
        this.mhtOrderNo = mhtOrderNo;
    }

    public String getMhtReqTime() {
        return mhtReqTime;
    }

    public void setMhtReqTime(String mhtReqTime) {
        this.mhtReqTime = mhtReqTime;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getFunCode() {
        return funCode;
    }

    @Override
    public String toString() {
        return "AccountBalanceQueryReqDTO [funCode=" + funCode + ", appId=" + appId + ", mhtOrderNo=" + mhtOrderNo + ", mhtReqTime="
                + mhtReqTime + ", accountType=" + accountType + "]";
    }

    public Map<String, String> toDataMap() {
        Map<String, String> rtnDataMap = new HashMap<String, String>();
        rtnDataMap.put("funcode", this.funCode);
        rtnDataMap.put("appId", this.getAppId());
        rtnDataMap.put("mhtOrderNo", this.getMhtOrderNo());
        rtnDataMap.put("mhtReqTime", this.getMhtReqTime());
        rtnDataMap.put("accountType", this.getAccountType());
        return rtnDataMap;
    }
}
