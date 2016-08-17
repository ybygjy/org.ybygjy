package org.ybygjy.pay.paynow.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
/**
 * 配置信息
 * @author WangYanCheng
 * @version 2016年6月18日
 */
public class Config {
    public static final String KEY_DES = "org.ybygjy.pay.paynow.key.des";
    public static final String KEY_MD5 = "org.ybygjy.pay.paynow.key.md5";
    public static final String KEY_APPID = "org.ybygjy.pay.paynow.appid";
    public static final String KEY_AGENTPAY_URL = "org.ybygjy.pay.paynow.agentpay.url";
    public static final String KEY_AGENTPAY_QUERY_URL = "org.ybygjy.pay.paynow.agentpay.query.url";
    public static final String KEY_ACCOUNTBALANCE_QUERY_URL = "org.ybygjy.pay.paynow.agentpay.accountbalance.query.url";
    private static String confFilePath = "gateway/pay-now.properties";
    private Properties properties = new Properties();
    private static final Config instance;
    static {
        instance = new Config();
    }

    private Config() {
        URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource(confFilePath);
        try {
            this.properties.load(new FileInputStream(resourceUrl.getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.properties.isEmpty()) {
            throw new RuntimeException("加载配置信息异常");
        }
    }

    public String getAttr(String key) {
        return this.properties.getProperty(key);
    }

    public String getAttr(String key, String defVal) {
        return this.properties.getProperty(key, defVal);
    }

    public static Config getInstance() {
        return instance;
    }
}
