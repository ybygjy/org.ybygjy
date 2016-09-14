package org.ybygjy.thrift.hello;

import java.io.IOException;

import org.apache.thrift.TApplicationException;
import org.apache.thrift.TException;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

/**
 * Thrift 实践，异步客户端
 * @author WangYanCheng
 * @version 2016年9月14日
 */
public class HelloThriftServerAsyncClient {
    /**
     * 测试入口
     * @param args 参数列表
     */
    public static void main(String[] args) {
        try {
            TAsyncClientManager clientManager = new TAsyncClientManager();
            TNonblockingTransport transport = new TNonblockingSocket("localhost", 10005);
            TProtocolFactory protocol = new TBinaryProtocol.Factory();
            HelloThrift.AsyncClient asyncClient = new HelloThrift.AsyncClient(protocol, clientManager, transport);
            System.out.println("Client calls .....");
            MethodCallback callback = new MethodCallback();
            asyncClient.helloBoolean(true, callback);
            while (callback.getResult() == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("HelloThrift.AsyncClient.Methods Invoke:::" + callback.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
            if (e instanceof TApplicationException && ((TApplicationException) e).getType() == TApplicationException.MISSING_RESULT) {
                System.out.println("The result of HelloThrift.helloNull function is NULL");
            }
        }
    }
}
