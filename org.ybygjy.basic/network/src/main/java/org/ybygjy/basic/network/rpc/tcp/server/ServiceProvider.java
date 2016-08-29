package org.ybygjy.basic.network.rpc.tcp.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.ybygjy.basic.network.rpc.tcp.HelloService;

/**
 * Service Provider
 * @author WangYanCheng
 * @version 2016年8月30日
 */
public class ServiceProvider {
    /** Service注册集合*/
    private Map<String, Object> servicesMap = new ConcurrentHashMap<String, Object>();
    private Executor executor = new ThreadPoolExecutor(5,10,3000,TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(50));
    /**
     * 测试入口
     * @param args
     */
    public static void main(String[] args) {
        ServiceProvider serviceProvider = new ServiceProvider();
        Object service = new HelloServiceImpl();
        serviceProvider.addService(HelloService.class.getName(), service);
        serviceProvider.doWork();
    }
    
    private Object getService(String intefaceName) {
        Object result = this.servicesMap.get(intefaceName);
        return result;
    }
    private Object addService(String serviceName, Object serviceObj) {
        return this.servicesMap.put(serviceName, serviceObj);
    }

    /**
     * 
     * @param socket
     */
    private void dispatchRequest(Socket socket) {
System.out.println(Thread.currentThread().getName() + "#Received a request=>" + socket.toString());
        this.executor.execute(new SocketThread(socket));
    }
    /**
     * socket任务处理线程,3秒keep alive
     * @author WangYanCheng
     * @version 2016年8月30日
     */
    class SocketThread implements Runnable {
        private Socket socket;
        private long lastReceivedTime = System.currentTimeMillis();
        private static final long receiveTimeOut = 30000;
        private volatile boolean runningFlag = true;
        public SocketThread(Socket socket) {
            this.socket = socket;
            try {
                this.socket.setKeepAlive(true);
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
        public void run() {
            while (runningFlag) {
                //计超时
                if (System.currentTimeMillis() - this.lastReceivedTime >= receiveTimeOut) {
                    this.runningFlag = false;
                    try {
                        this.socket.close();
                        continue;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    InputStream ins = socket.getInputStream();
                    if (ins.available() == 0) {
                        continue;
                    }
                    ObjectInputStream ois = new ObjectInputStream(ins);
                    String interfaceName = ois.readUTF();
                    String methodName = ois.readUTF();
                    Class<?>[] parameterTypes = (Class<?>[]) ois.readObject();
                    Object[] arguments = (Object[]) ois.readObject();
                    
                    Class serviceInterfaceClass = Class.forName(interfaceName);
                    Object service = ServiceProvider.this.getService(interfaceName);
                    Method method;
                    method = serviceInterfaceClass.getMethod(methodName, parameterTypes);
                    Object result;
                    result = method.invoke(service, arguments);
                    ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
                    ous.writeObject(result);
System.out.println("Send a response=>" + result);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void doWork() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8899);
System.out.println("listing 8899");
            while (true) {
                Socket socket = serverSocket.accept();
                this.dispatchRequest(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
