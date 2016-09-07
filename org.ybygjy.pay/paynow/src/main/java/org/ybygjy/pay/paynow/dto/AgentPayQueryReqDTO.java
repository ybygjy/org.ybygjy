package org.ybygjy.pay.paynow.dto;

import java.util.HashMap;
import java.util.Map;

import org.ybygjy.pay.dto.AbstractPayReqDTO;

/**
 * 代付交易状态查询
 * @author WangYanCheng
 * @version 2016年8月4日
 */
public class AgentPayQueryReqDTO extends AbstractPayReqDTO {
    /** 功能编码 */
    private String funCode = "AP07";
    /** 商户应用唯一标识 */
    private String appId;
    /** 商户请求流水号 */
    private String mhtOrderNo;
    /** 商户请求时间 */
    private String mhtReqTime;

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

    public String getFunCode() {
        return funCode;
    }

    @Override
    public String toString() {
        return "AgentPayQueryReqDTO [funCode=" + funCode + ", appId=" + appId + ", mhtOrderNo=" + mhtOrderNo + ", mhtReqTime=" + mhtReqTime
                + "]";
    }

    public Map<String,String> toDataMap() {
        Map<String, String> rtnDataMap = new HashMap<String, String>();
        rtnDataMap.put("funcode", this.getFunCode());
        rtnDataMap.put("appId", this.getAppId());
        rtnDataMap.put("mhtOrderNo", this.getMhtOrderNo());
        rtnDataMap.put("mhtReqTime", this.getMhtReqTime());
        return rtnDataMap;
    }
}
