package org.ybygjy.thrift.hello;

import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TNonblockingServer.Args;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
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
//        try {
//            TServerSocket serverSocket = new TServerSocket(7911);
//            //协议工厂
//            Factory protocolFactory = new TBinaryProtocol.Factory();
//            TProcessor processor = new HelloThrift.Processor<Iface>(new HelloThriftServiceImpl());
//            TServer server = new TThreadPoolServer(new Args(serverSocket).processor(processor).protocolFactory(protocolFactory));
//            System.out.println("Starting the thrift server!");
//            server.serve();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        TNonblockingServerTransport serverTransport;
        try {
            serverTransport = new TNonblockingServerSocket(10005);
            HelloThrift.Processor<Iface> processor = new HelloThrift.Processor<Iface>(new HelloThriftServiceImpl());
            TServer tServer = new TNonblockingServer(new Args(serverTransport).processor(processor));
            System.out.println("Start Server on port 10005");
            tServer.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
