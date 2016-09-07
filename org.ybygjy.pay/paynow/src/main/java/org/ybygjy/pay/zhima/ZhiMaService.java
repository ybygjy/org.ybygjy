package org.ybygjy.pay.zhima;

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
        return null;
    }
}
