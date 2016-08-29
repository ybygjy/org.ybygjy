package org.ybygjy.basic.network.rpc.tcp.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;

import org.ybygjy.basic.network.rpc.tcp.HelloService;

/**
 * Service Consumer
 * @author WangYanCheng
 * @version 2016年8月30日
 */
public class Consumer {
    public void doWork() {
        String interfaceName = HelloService.class.getName();
        Socket socket = null;
        try {
            Method method = HelloService.class.getMethod("sayHello", java.lang.String.class);
            Object[] arguments = {"HelloService"};
            socket = new Socket("127.0.0.1", 8899);
            ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
            ous.writeUTF(interfaceName);
            ous.writeUTF(method.getName());
            ous.writeObject(method.getParameterTypes());
            ous.writeObject(arguments);
            InputStream inst = socket.getInputStream();
//            if (inst.available() == 0) {
//                System.out.println(Thread.currentThread().getName() + "#Received a invalid response");
//                return;
//            }
            ObjectInputStream ois = new ObjectInputStream(inst);
            Object result = ois.readObject();
            System.out.println(Thread.currentThread().getName() + "#service result =>" + result);
            
            ous = new ObjectOutputStream(socket.getOutputStream());
            ous.writeUTF(interfaceName);
            ous.writeUTF(method.getName());
            ous.writeObject(method.getParameterTypes());
            ous.writeObject(new Object[]{"bye"});
            ous.flush();
            
            ois = new ObjectInputStream(socket.getInputStream());
            result = ois.readObject();
            System.out.println(Thread.currentThread().getName() + "#service result =>" + result);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != socket) {
                try {
                    socket.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                public void run() {
                    new Consumer().doWork();
                }
            }).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }
}
