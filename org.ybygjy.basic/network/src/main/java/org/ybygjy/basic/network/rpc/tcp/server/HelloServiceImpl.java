package org.ybygjy.basic.network.rpc.tcp.server;

import org.ybygjy.basic.network.rpc.tcp.HelloService;

public class HelloServiceImpl implements HelloService {
    public String sayHello(String helloStr) {
        if ("HelloService".equals(helloStr)) {
            return "Hello Client";
        } else {
            return "bye by Client";
        }
    }
}
