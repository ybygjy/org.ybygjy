package org.ybygjy.thrift;

import java.net.URL;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.ybygjy.thrift.shared.SharedStruct;
import org.ybygjy.thrift.tutorial.Calculator;
import org.ybygjy.thrift.tutorial.Operation;
import org.ybygjy.thrift.tutorial.Work;

/**
 * Thrift Client
 * @author WangYanCheng
 * @version 2016年9月6日
 */
public class JavaClient {
    public static void main(String[] args) {
        args = new String[]{"simple"};
        if (args.length != 1) {
            System.out.println("Please enter 'simple' or 'secure'");
            System.exit(0);
        }
        TTransport transport = null;
        if (args[0].contains("simple")) {
            transport = new TSocket("localhost", 9090);
            try {
                transport.open();
            } catch (TTransportException e) {
                e.printStackTrace();
            }
        } else {
            URL url = JavaClient.class.getClassLoader().getResource("lib-java-test-.truststore");
            TSSLTransportParameters params = new TSSLTransportParameters();
            params.setTrustStore(url.getFile(), "thrift", "SunX509", "JKS");
            try {
                transport = TSSLTransportFactory.getClientSocket("localhost", 9091, 0, params);
            } catch (TTransportException e) {
                e.printStackTrace();
            }
        }
        TProtocol protocol = new TBinaryProtocol(transport);
        Calculator.Client client = new Calculator.Client(protocol);
        try {
            perform(client);
        } catch (TException e) {
            e.printStackTrace();
        }
        transport.close();
    }
    private static void perform(Calculator.Client client) throws TException {
        client.ping();
        System.out.println("ping()");
//        int sum = client.add(1, 1);
//        System.out.println("1+1=" + sum);
        
        Work work = new Work();
        work.op = Operation.DIVIDE;
        work.num1 = 1;
        work.num2 = 1;
        
        int quotient = client.calculate(1, work);
        System.out.println("Whoa we can divide by 0");
        work.op = Operation.SUBTRACT;
        work.num1 = 15;
        work.num2 = 10;
        int diff = client.calculate(1, work);
        System.out.println("15-10=" + diff);
        SharedStruct log = client.getStruct(1);
        System.out.println("Check log : " + log.value);
    }
}
