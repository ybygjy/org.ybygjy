package org.ybygjy.pay.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 封装http/https通信
 * @author WangYanCheng
 * @version 2016年8月17日
 */
public class HttpClient {
    public static final int CONN_TIMEOUT = 5000;
    public static final int CONN_READTIMEOUT = 10000;
    public String send(String serviceUrl, String dataStr, String charset) throws IOException {
        URL urlInst = new URL(serviceUrl);
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) urlInst.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(CONN_TIMEOUT);
            urlConnection.setReadTimeout(CONN_READTIMEOUT);
            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            BufferedWriter buffOus = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), charset));
            buffOus.write(dataStr);
            buffOus.flush();
            buffOus.close();
            int connStatus = urlConnection.getResponseCode();
            if (connStatus == 200) {
                InputStream ins = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buff = new byte[1024];
                int flag = -1;
                while ((flag = ins.read(buff)) != -1) {
                    baos.write(buff, 0, flag);
                }
                ins.close();
                baos.close();
                return baos.toString(charset);
            }
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}
