package org.ybygjy.basic.basic;

/**
 * Created by leye on 2017/9/1.
 */
public class SingleThreadRingBuffer {
    private static final int bufferSize = 1024;
    private String[] buffer = new String[bufferSize];
    private int head = 0;
    private int tail = 0;

    private boolean empty() {
        return head == tail;
    }
    private boolean full() {
        return (tail + 1) % bufferSize == head;
    }
    public boolean put(String val) {
        if (full()) {
            return false;
        }
        buffer[tail] = val;
        tail = (tail + 1) % bufferSize;
        return true;
    }
    public String get() {
        if (empty()) {
            return null;
        }
        String result = buffer[head];
        head = (head + 1) % bufferSize;
        return result;
    }
    public String[] getAll() {
        if (empty()) {
            return new String[0];
        }
        int copyTail = tail;
        int cnt =head < copyTail ? copyTail - head : bufferSize - head + copyTail;
        String[] result = new String[cnt];
        if (head < copyTail) {
            for (int i = head; i < copyTail; i++) {
                result[i - head] = buffer[i];
            }
        } else {
            for (int i = head; i < bufferSize; i++) {
                result[i - head] = buffer[i];
            }
            for (int i = 0; i < copyTail; i++) {
                result[bufferSize - head + i] = buffer[i];
            }
        }
        head = copyTail;
        return result;
    }

    public static void main(String[] args) {
        SingleThreadRingBuffer singleThreadRingBuffer = new SingleThreadRingBuffer();
        int i = 0;
        new Thread(()->{
            while (true) {
                singleThreadRingBuffer.put("Key_" + i);
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            while (true) {
                String value = singleThreadRingBuffer.get();
                System.out.println("value=>" + value);
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
