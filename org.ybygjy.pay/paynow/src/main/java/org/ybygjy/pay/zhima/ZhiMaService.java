package org.ybygjy.pay.zhima;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.ybygjy.pay.dto.AbstractPayReqDTO;

/**
 * 芝麻服务接口
 * @author WangYanCheng
 * @version 2016年9月7日
 */
public class ZhiMaService {
    /**
     * 初始化接口
     * @param dataMap
     * @return rtnStr
     */
    public String certifyInitial(AbstractPayReqDTO reqDto) {
        String serviceUrl = reqDto.getServiceUri();
        Map<String, String> dataMap = new TreeMap<String, String>();
        dataMap.put("app_id", "");
        dataMap.put("charset", "utf-8");
        dataMap.put("method", "zhima.customer.certify.initial");
        dataMap.put("version", "1.0");
        dataMap.put("channel", "api");
        dataMap.put("platform", "zmop");
        dataMap.put("params", "");
        dataMap.put("sign", "");
        dataMap.put("ext_params", "");
        Map<String, String> innerDataMap = new TreeMap<String, String>();
        innerDataMap.put("transaction_id", "");
        innerDataMap.put("contract_flag", "");
        innerDataMap.put("product_code", "");
        innerDataMap.put("identity_type", "");
        innerDataMap.put("identity_param", "");
        innerDataMap.put("state", "");
        innerDataMap.put("biz_params", "");
        innerDataMap.put("source_type", "");
        innerDataMap.put("page_url", "");
        innerDataMap.put("schema_url", "");
        return null;
    }
    private String generateKey2Value(Map<String, String> dataMap) {
        StringBuilder sbud = new StringBuilder();
        for (Iterator<String> iterator = dataMap.keySet().iterator(); iterator.hasNext();) {
            String keys = iterator.next();
            try {
                sbud.append(keys).append("&").append(URLEncoder.encode(dataMap.get(keys), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sbud.toString();
    }
}
