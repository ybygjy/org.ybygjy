package org.ybygjy.thrift;

import java.net.URL;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.ybygjy.thrift.tutorial.Calculator;
import org.ybygjy.thrift.tutorial.Calculator.Iface;

/**
 * Thrift 学习
 * @author WangYanCheng
 * @version 2016年9月6日
 */
public class JavaServer {
    public static CalculatorHandler handler;
    public static Calculator.Processor processor;
    public static void main(String[] args) {
        handler = new CalculatorHandler();
        processor = new Calculator.Processor(handler);
        Runnable simple = new Runnable() {
            public void run() {
                simple(processor);
            }
        };
        Runnable secure = new Runnable() {
            public void run() {
                secure(processor);
            }
        };
        new Thread(simple).start();
        new Thread(secure).start();
    }
    public static void simple(Calculator.Processor<Iface> processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void secure(Calculator.Processor<Iface> processor) {
        try {
            URL url = JavaServer.class.getClassLoader().getResource("lib-java-test-.keystore");
System.out.println(url);
            TSSLTransportParameters param = new TSSLTransportParameters();
            param.setKeyStore(url.getFile(), "thrift", null, null);
            TServerTransport serverTransport = TSSLTransportFactory.getServerSocket(9091, 0, null, param);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
            System.out.println("Starting the secure server ...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
