package org.ybygjy.basic.network.rpc.lb;

import java.util.HashMap;
import java.util.Map;

/**
 * 负载均衡计算
 * @author WangYanCheng
 * @version 2016年8月30日
 */
public abstract class AbstractLoadBalance {
    protected static Map<String, Integer> serverMap = new HashMap<String, Integer>();
    static {
        serverMap.put("10.20.1.10", 1);
        serverMap.put("10.20.1.11", 1);
        serverMap.put("10.20.1.12", 1);
        serverMap.put("10.20.1.13", 4);
        serverMap.put("10.20.1.14", 4);
        serverMap.put("10.20.1.15", 4);
        serverMap.put("10.20.1.16", 4);
        serverMap.put("10.20.1.17", 4);
        serverMap.put("10.20.1.18", 4);
        serverMap.put("10.20.1.19", 4);
        serverMap.put("10.20.11.10", 4);
    }
    /**
     * 取服务地址
     * @return rtnAddr
     */
    public abstract String getServerAddr();
}
