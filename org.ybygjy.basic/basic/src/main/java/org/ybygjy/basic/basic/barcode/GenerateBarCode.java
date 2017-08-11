package org.ybygjy.basic.basic.barcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;

/**
 * 二维码生成
 * Created by leye on 2017/8/10.
 */
public class GenerateBarCode {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static String IMGTYPE = "jpeg";
    public void generateBarCode(String data, OutputStream out) {
        int width = 200;
        int height = 200;
        try {
            BitMatrix byteMatrix = new MultiFormatWriter().encode(new String(data.getBytes(), Charset.forName("UTF-8")), BarcodeFormat.QR_CODE, width, height);
            BufferedImage bufferedImage = new BufferedImage(byteMatrix.getWidth(), byteMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < byteMatrix.getWidth(); x++) {
                for (int y = 0; y < byteMatrix.getHeight(); y++) {
                    bufferedImage.setRGB(x, y, byteMatrix.get(x, y) ? BLACK : WHITE);
                }
            }
            Graphics graphics = bufferedImage.getGraphics();
            graphics.setColor(Color.black);
            graphics.drawString("HelloWorld", 10, 10);
            bufferedImage.flush();
            ImageIO.write(bufferedImage, IMGTYPE, out);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String stationId = "100262";
        String itemId = "9610101";
        String actId = "9610101";
        String url = "http://wapp.wapa.taobao.com/yizhan/sendSamples.html?stationId=" + stationId + "&itemId=" + itemId + "&actId=" + actId;
        File outFile = new File("barCode_" + stationId + "_" + itemId + "." + IMGTYPE);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile, true));
            new GenerateBarCode().generateBarCode(url, bos);
            bos.flush();
            bos.close();
System.out.printf(outFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
