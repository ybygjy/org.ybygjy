package org.ybygjy.thrift.hello;

import org.apache.thrift.TException;

/**
 * HelloThrift
 * <p>服务接口定义HelloThrift.Iface</p>
 * <p>客户端调用逻辑HelloThrift.Client</p>
 * <p>服务端调用逻辑HelloThrift.Processor</p>
 * @author WangYanCheng
 * @version 2016年9月13日
 */
public class HelloThriftServiceImpl implements HelloThrift.Iface {

    @Override
    public String helloString(String para) throws TException {
        System.out.println("HelloString");
        return "HelloString";
    }

    @Override
    public int helloInt(int param) throws TException {
        System.out.println("HelloInt");
        return 0;
    }

    @Override
    public boolean helloBoolean(boolean param) throws TException {
        System.out.println("HelloBoolean");
        return false;
    }

    @Override
    public void helloVoid() throws TException {
        System.out.println("HelloVoid");
    }

    @Override
    public String helloNull() throws TException {
        System.out.println("HelloNull");
        return "HelloNull";
    }
}
