package org.ybygjy.pay.paynow.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 抽象交易请求实体
 * @author WangYanCheng
 * @version 2016年8月3日
 */
public class AbstractPayReqDTO {
    /** 请求时间 */
    private Date requestTime;
    /** 请求方法 */
    private String method;
    /** 服务地址 */
    private String serviceUri;
    /** 业务线Code */
    private String businessCode;
    /** 连接超时时间 */
    private int connectionTimeout;
    /** 读取超时时间 */
    private int readTimeout;
    /** 请求报文字符编码*/
    private String inputCharset = "UTF-8";
    /** 扩展数据 */
    private Map<String, String> extDataMap = new HashMap<String, String>();

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getServiceUri() {
        return serviceUri;
    }

    public void setServiceUri(String serviceUri) {
        this.serviceUri = serviceUri;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Map<String, String> getExtDataMap() {
        return extDataMap;
    }

    public void setExtDataMap(Map<String, String> extDataMap) {
        this.extDataMap = extDataMap;
    }

    public String getInputCharset() {
        return inputCharset;
    }

    public void setInputCharset(String inputCharset) {
        this.inputCharset = inputCharset;
    }

    @Override
    public String toString() {
        return "AbstractPayReqDTO [requestTime=" + requestTime + ", method=" + method + ", serviceUri=" + serviceUri + ", businessCode="
                + businessCode + ", connectionTimeout=" + connectionTimeout + ", readTimeout=" + readTimeout + ", inputCharset="
                + inputCharset + ", extDataMap=" + extDataMap + "]";
    }
}
