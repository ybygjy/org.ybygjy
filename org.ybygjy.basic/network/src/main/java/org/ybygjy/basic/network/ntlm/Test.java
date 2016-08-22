package org.ybygjy.basic.network.ntlm;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import sun.misc.BASE64Encoder;

public class Test {
    public void addAuthentication(URLConnection urlConn, String userName, String password) {
        String value = new BASE64Encoder().encode(userName.concat(password).getBytes());
        urlConn.setRequestProperty("Authentication", "Basic " + value);
    }
    public void doWork() throws Exception {
        URL urlInst = new URL("");
        URLConnection conn = urlInst.openConnection();
        InputStream ins = conn.getInputStream();
        byte[] buff = new byte[1024];
        int i = -1;
        StringBuilder sbud = new StringBuilder();
        while ((i = ins.read(buff)) != -1) {
            sbud.append(new String(buff, 0, i));
        }
        System.out.println(sbud);
        ins.close();
        conn.getOutputStream().write(new byte[]{'H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd'});
        conn.getOutputStream().flush();
        System.out.println("Finish");
    }
    public static void main(String[] args) throws Exception {
        Test test = new Test();
        test.doWork();
    }
}
