package org.ybygjy.thrift.hello;

import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TNonblockingServer.Args;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.ybygjy.thrift.hello.HelloThrift.Iface;

/**
 * @author WangYanCheng
 * @version 2016年9月14日
 */
public class HelloThriftAsynServer {
    /**
     * 测试入口
     * @param args args
     */
    public static void main(String[] args) {
        TNonblockingServerTransport serverTransport;
        try {
            serverTransport = new TNonblockingServerSocket(10005);
            HelloThrift.Processor<Iface> processor = new HelloThrift.Processor<Iface>(new HelloThriftServiceImpl());
            TServer tserver = new TNonblockingServer(new Args(serverTransport).processor(processor));
            System.out.println("Start server port 10005");
            tserver.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
