package org.ybygjy.pay.dto;

/**
 * 账户余额查询响应
 * @author WangYanCheng
 * @version 2016年8月4日
 */
public class AccountBalanceQueryRepDTO extends AbstractPayRepDTO {
    private String funCode;
    private String appId;
    private String mhtOrderNo;
    private String nowPayOrderNo;
    private String responseTime;
    private String responseCode;
    private String responseMsg;
    private int accountBalance;

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

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "AccountBalanceQueryRepDTO [funCode=" + funCode + ", appId=" + appId + ", mhtOrderNo=" + mhtOrderNo + ", nowPayOrderNo="
                + nowPayOrderNo + ", responseTime=" + responseTime + ", responseCode=" + responseCode + ", responseMsg=" + responseMsg
                + ", accountBalance=" + accountBalance + "]";
    }
}
