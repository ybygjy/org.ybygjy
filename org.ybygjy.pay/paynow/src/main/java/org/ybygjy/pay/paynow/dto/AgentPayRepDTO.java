package org.ybygjy.pay.paynow.dto;


/**
 * 代付交易响应实体
 * @author WangYanCheng
 * @version 2016年8月2日
 */
public class AgentPayRepDTO extends AbstractPayRepDTO {
    /** 功能码 */
    private String funCode;
    /** 商户应用唯一标识 */
    private String appId;
    /** 商户请求流水号 */
    private String mhtOrderNo;
    /** 现在支付流水号 */
    private String nowPayOrderNo;
    /** 响应时间 */
    private String responseTime;
    /** 响应码 */
    private String responseCode;
    /** 响应信息 */
    private String responseMsg;
    /** 交易状态 */
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

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    @Override
    public String toString() {
        return "AgentPayRepDTO [funCode=" + funCode + ", appId=" + appId + ", mhtOrderNo=" + mhtOrderNo + ", nowPayOrderNo="
                + nowPayOrderNo + ", responseTime=" + responseTime + ", responseCode=" + responseCode + ", responseMsg=" + responseMsg
                + ", tradeStatus=" + tradeStatus + "]";
    }
}
