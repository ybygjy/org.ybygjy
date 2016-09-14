package org.ybygjy.thrift.hello;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.ybygjy.thrift.hello.HelloThrift.Iface;

/**
 * HelloThrift
 * <p>Thrift服务器实现</p>
 * @author WangYanCheng
 * @version 2016年9月13日
 */
public class HelloThriftServer {
    /**
     * 测试入口#启动Thrift服务器
     * @param args
     */
    public static void main(String[] args) {
        try {
            TServerSocket serverSocket = new TServerSocket(7911);
            //协议工厂
            Factory protocolFactory = new TBinaryProtocol.Factory();
            TProcessor processor = new HelloThrift.Processor<Iface>(new HelloThriftServiceImpl());
            TServer server = new TThreadPoolServer(new Args(serverSocket).processor(processor).protocolFactory(protocolFactory));
            System.out.println("Starting the thrift server!");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
