package org.ybygjy.thrift.hello;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * HelloThrift
 * @author WangYanCheng
 * @version 2016年9月14日
 */
public class HelloThriftClient {
    /**
     * 测试入口
     * @param args 参数列表
     */
    public static void main(String[] args) {
        try {
            TTransport transport = new TSocket("localhost", 7911);
            transport.open();
            //传输协议为TBinaryProtocol
            TProtocol protocol = new TBinaryProtocol(transport);
            HelloThrift.Client client = new HelloThrift.Client(protocol);
            client.helloBoolean(true);
            client.helloInt(10);
            client.helloNull();
            client.helloString("Hello Thrift");
            client.helloVoid();
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
