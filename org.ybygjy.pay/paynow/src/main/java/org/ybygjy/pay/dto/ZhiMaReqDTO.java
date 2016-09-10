package org.ybygjy.pay.dto;

/**
 * 芝麻服务请求体
 * @author WangYanCheng
 * @version 2016年9月8日
 */
public class ZhiMaReqDTO extends AbstractPayReqDTO {
    /** 商户技术开发自助创建的应用标识 */
    private String appId;
    /** 要调用的接口名 */
    private String method;
    /** 接口版本 */
    private String version = "1.0";
    /** {api:商户后台调用;apppc:商户PC端调用;app:商户移动端调用} */
    private String channel = "api";
    /** 来源平台,默认为zmop */
    private String plateform = "zmop";
    /** RSA加密后的业务参数 */
    private String params;
    /** params加密前的签名SHA1WithRSA */
    private String sign;
    /** 扩展参数 */
    private String extParams;

    /** 业务流水号 */
    private String transactionId;
    /** 与芝麻信用签定的合约外标（联系芝麻技术获取） */
    private String contractFlag;
    /** 当前使用的产品码（联系芝麻技术获取） */
    private String productCode;
    /** 身标识别类型{BY_CERTNO_AND_NAME:身份证＋姓名;BY_MOBILE_NO:手机号;BY_CERT_IMAGE:证件图片识别} */
    private String identityType;
    /** 不同身份类型的参数列表,json格式*/
    private String identityParam;
    /** 芝麻认证过程的冗余字段，在认证申请时传入，回调时原样响应给平台*/
    private String state;
    /** 业务扩展参数入参*/
    private String bizParams;
    /** 请求来源类型{h5/pc/app/sdk/window}*/
    private String sourceType;
    /** 商户页面回调地址，芝麻认证完成后通过此URL客户端重定向传递认证结果*/
    private String pageUrl;
    /** 商户App回调地址，通过商户App发起人脸核身认证时必传*/
    private String schemaUrl;
    /** 商户RSA私钥*/
    private transient String privateKey;
    /** 芝麻RSA公钥*/
    private transient String zhiMaPublicKey;
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getPlateform() {
        return plateform;
    }
    public void setPlateform(String plateform) {
        this.plateform = plateform;
    }
    public String getParams() {
        return params;
    }
    public void setParams(String params) {
        this.params = params;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getExtParams() {
        return extParams;
    }
    public void setExtParams(String extParams) {
        this.extParams = extParams;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getContractFlag() {
        return contractFlag;
    }
    public void setContractFlag(String contractFlag) {
        this.contractFlag = contractFlag;
    }
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getIdentityType() {
        return identityType;
    }
    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }
    public String getIdentityParam() {
        return identityParam;
    }
    public void setIdentityParam(String identityParam) {
        this.identityParam = identityParam;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getBizParams() {
        return bizParams;
    }
    public void setBizParams(String bizParams) {
        this.bizParams = bizParams;
    }
    public String getSourceType() {
        return sourceType;
    }
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
    public String getPageUrl() {
        return pageUrl;
    }
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
    public String getSchemaUrl() {
        return schemaUrl;
    }
    public void setSchemaUrl(String schemaUrl) {
        this.schemaUrl = schemaUrl;
    }
    
    public String getPrivateKey() {
        return privateKey;
    }
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
    public String getZhiMaPublicKey() {
        return zhiMaPublicKey;
    }
    public void setZhiMaPublicKey(String zhiMaPublicKey) {
        this.zhiMaPublicKey = zhiMaPublicKey;
    }
    @Override
    public String toString() {
        return "ZhiMaReqDTO [appId=" + appId + ", method=" + method + ", version=" + version + ", channel=" + channel + ", plateform="
                + plateform + ", params=" + params + ", sign=" + sign + ", extParams=" + extParams + ", transactionId=" + transactionId
                + ", contractFlag=" + contractFlag + ", productCode=" + productCode + ", identityType=" + identityType + ", identityParam="
                + identityParam + ", state=" + state + ", bizParams=" + bizParams + ", sourceType=" + sourceType + ", pageUrl=" + pageUrl
                + ", schemaUrl=" + schemaUrl + "]";
    }
}
