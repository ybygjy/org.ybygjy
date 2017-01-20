package org.ybygjy.basic.basic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Enum
 */
public class EnumTest {
    private Client client = new Client();
    private Server server = new Server();
    public void doWork(int flag) {
        if (1 == flag) {
            client.doWork();
        } else {
            server.doWork();
        }
    }
    public static void main(String[] args) {
        new EnumTest().doWork(Integer.parseInt(args[0]));
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    enum WeekDayEnum {
        Mon(1), Tue(2), Wed(3), Thu(4), Fri(5), Sat(6), Sun(7);
        private int index;
        private WeekDayEnum(int idx) {
            this.index = idx;
        }
        public int getIndex() {
            return this.index;
        }
    }
    /**
     * Created by leye on 2017/1/19.
     */
    class Client {
        public void doWork() {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress("127.0.0.1", 8999));
                ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
                ous.writeObject(WeekDayEnum.Fri);
                ous.flush();
                ous.close();
                System.out.println("Complete");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Server {
        public void doWork() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8999);
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                WeekDayEnum weekDayEnum = ((EnumTest.WeekDayEnum) ois.readObject());
                ois.close();
                socket.close();
                if (weekDayEnum == WeekDayEnum.Fri) {
                    System.out.println("Client Friday Enum value is same as server's.");
                }
                if (weekDayEnum.equals(WeekDayEnum.Fri)) {
                    System.out.println("Client Friday Enum value is equals to server's");
                } else {
                    System.out.println("Client Friday Enum value is not same as server's");
                }
                switch (weekDayEnum) {
                    case Fri:
                        System.out.println("Do Friday work.");
                        break;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
