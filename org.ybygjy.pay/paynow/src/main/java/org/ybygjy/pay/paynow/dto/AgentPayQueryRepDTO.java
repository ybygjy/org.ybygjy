package org.ybygjy.pay.paynow.dto;

/**
 * 代付交易查询响应
 * @author WangYanCheng
 * @version 2016年8月4日
 */
public class AgentPayQueryRepDTO extends AgentPayRepDTO {
    private String funCode;
    private String appId;
    private String mhtOrderNo;
    private String nowPayOrderNo;
    private String responseTime;
    private String responseCode;
    private String responseMsg;
    private String payeeCardNo;
    private String payeeCardUnionNo;
    private String payeeAccType;
    private String payeeName;
    private String tradeStatus;

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

    public String getNowPayOrderNo() {
        return nowPayOrderNo;
    }

    public void setNowPayOrderNo(String nowPayOrderNo) {
        this.nowPayOrderNo = nowPayOrderNo;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
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

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    @Override
    public String toString() {
        return "AgentPayQueryRepDTO [funCode=" + funCode + ", appId=" + appId + ", mhtOrderNo=" + mhtOrderNo + ", nowPayOrderNo="
                + nowPayOrderNo + ", responseTime=" + responseTime + ", responseCode=" + responseCode + ", responseMsg=" + responseMsg
                + ", payeeCardNo=" + payeeCardNo + ", payeeCardUnionNo=" + payeeCardUnionNo + ", payeeAccType=" + payeeAccType
                + ", payeeName=" + payeeName + ", tradeStatus=" + tradeStatus + "]";
    }
}
