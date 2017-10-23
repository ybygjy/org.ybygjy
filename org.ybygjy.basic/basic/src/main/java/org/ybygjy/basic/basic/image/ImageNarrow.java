package org.ybygjy.basic.basic.image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图像缩放
 * Created by leye on 2017/10/11.
 */
public class ImageNarrow {
    private String imagePathSrc;
    private String imagePathTarget;

    public ImageNarrow(String imagePathSrc, String imagePathTarget) {
        this.imagePathSrc = imagePathSrc;
        this.imagePathTarget = imagePathTarget;
    }
    public void doWork() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(imagePathSrc));
            int srcWidth = bufferedImage.getWidth();
            int srcHeight = bufferedImage.getHeight();
            //缩小尺寸比例
            float resizeRate = 1f;
            int targetWidth = (int) (srcWidth * resizeRate);
            int targetHeight = (int) (srcHeight * resizeRate);
            BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
            targetImage.getGraphics().drawImage(bufferedImage.getScaledInstance(targetWidth, targetHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
            FileOutputStream fous = new FileOutputStream(imagePathTarget);
            JPEGImageEncoder jpegImageEncoder = JPEGCodec.createJPEGEncoder(fous);
            JPEGEncodeParam jpegEncodeParam = JPEGCodec.getDefaultJPEGEncodeParam(targetImage);
            jpegEncodeParam.setQuality(0.85F, true);
            jpegImageEncoder.encode(targetImage, jpegEncodeParam);
            fous.flush();
            fous.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ImageNarrow("/Users/leye/Documents/1001.jpg", "/Users/leye/Documents/1001.narrow.jpg").doWork();
    }
}
