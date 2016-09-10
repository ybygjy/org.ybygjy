package org.ybygjy.pay.dto;

/**
 * 芝麻响应
 * @author WangYanCheng
 * @version 2016年9月9日
 */
public class ZhiMaRepDTO extends AbstractPayRepDTO {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
