package org.ybygjy.pay.paynow.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 代付交易请求实体
 * @author WangYanCheng
 * @version 2016年8月2日
 */
public class AgentPayReqDTO extends AbstractPayReqDTO {
    /** 功能码 */
    public String funCode = "AP04";
    /** AppId */
    public String appId;
    /** 商户请求流水号 */
    public String mhtOrderNo;
    /** 商户请求时间 */
    public String mhtReqTime;
    /** 入账账户类型{01:对公;02:对私} */
    public String payeeAccType;
    /** 入账账户户名 */
    public String payeeName;
    /** 入账账户账号 */
    public String payeeCardNo;
    /** 入账账户联行号 */
    public String payeeCardUnionNo;
    /** 代付金额(单位分) */
    public int mhtOrderAmt;
    /** 资金用途(代付原因,备注) */
    public String agentPayMemo;

    public String getFunCode() {
        return funCode;
    }

    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

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

    public String getPayeeAccType() {
        return payeeAccType;
    }

    public void setPayeeAccType(String payeeAccType) {
        this.payeeAccType = payeeAccType;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeCardNo() {
        return payeeCardNo;
    }

    public void setPayeeCardNo(String payeeCardNo) {
        this.payeeCardNo = payeeCardNo;
    }

    public String getPayeeCardUnionNo() {
        return payeeCardUnionNo;
    }

    public void setPayeeCardUnionNo(String payeeCardUnionNo) {
        this.payeeCardUnionNo = payeeCardUnionNo;
    }

    public int getMhtOrderAmt() {
        return mhtOrderAmt;
    }

    public void setMhtOrderAmt(int mhtOrderAmt) {
        this.mhtOrderAmt = mhtOrderAmt;
    }

    public String getAgentPayMemo() {
        return agentPayMemo;
    }

    public void setAgentPayMemo(String agentPayMemo) {
        this.agentPayMemo = agentPayMemo;
    }

    @Override
    public String toString() {
        return "AgentPayReqDTO [funCode=" + funCode + ", appId=" + appId + ", mhtOrderNo=" + mhtOrderNo + ", mhtReqTime=" + mhtReqTime
                + ", payeeAccType=" + payeeAccType + ", payeeName=" + payeeName + ", payeeCardNo=" + payeeCardNo + ", payeeCardUnionNo="
                + payeeCardUnionNo + ", mhtOrderAmt=" + mhtOrderAmt + ", agentPayMemo=" + agentPayMemo + "]";
    }

    public Map<String, String> toDataMap() {
        Map<String, String> rtnDataMap = new HashMap<String, String>();
        rtnDataMap.put("funcode", this.funCode);
        rtnDataMap.put("appId", this.getAppId());
        rtnDataMap.put("mhtOrderNo", this.getMhtOrderNo());
        rtnDataMap.put("mhtReqTime", this.getMhtReqTime());
        rtnDataMap.put("payeeAccType", this.getPayeeAccType());
        rtnDataMap.put("payeeName", this.getPayeeName());
        rtnDataMap.put("payeeCardNo", this.getPayeeCardNo());
        rtnDataMap.put("payeeCardUnionNo", this.getPayeeCardUnionNo());
        rtnDataMap.put("mhtOrderAmt", String.valueOf(this.mhtOrderAmt));
        rtnDataMap.put("agentPayMemo", this.getAgentPayMemo());
        return rtnDataMap;
    }

    public AgentPayReqDTO parse(Map<String, String> dataMap) {
        return null;
    }
}
