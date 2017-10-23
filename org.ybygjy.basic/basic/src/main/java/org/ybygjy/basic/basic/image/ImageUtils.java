package org.ybygjy.basic.basic.image;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * 封装Java图像处理
 * Created by leye on 2017/10/11.
 */
public class ImageUtils {
    public static int[] getImageSizeInfo(File imageFile) {
        if (!imageFile.exists()) {
            throw new RuntimeException("File not found.".concat(imageFile.getAbsolutePath()));
        }
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(imageFile));
            BufferedImage bufferedImage = ImageIO.read(bis);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            return new int[]{width, height};
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 重新设置图片文件尺寸
     * @param srcImage 源文件
     * @param destFile 目标文件(尺寸调整后文件写入地址)
     * @param newWidth 图片宽度
     * @param newHeight 图片高度
     */
    public static void resizeImage(File srcImage, File destFile, int newWidth, int newHeight) {
        if (!srcImage.exists()) {
            throw new RuntimeException("File Not Found!");
        }
        if (newWidth < 1 || newHeight < 1) {
            throw new RuntimeException("Invalid Width | Height.");
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcImage));
            BufferedImage bufferedImage = ImageIO.read(bis);
            boolean hasNotAlpha = !bufferedImage.getColorModel().hasAlpha();

            BufferedImage targetImage = new BufferedImage(newWidth, newHeight, hasNotAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
            targetImage.getGraphics().drawImage(bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            bos = new BufferedOutputStream(new FileOutputStream(destFile));
            ImageIO.write(targetImage, hasNotAlpha ? "jpg" : "png", bos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 图片裁剪
     * @param imageFile 源图
     * @param destFile 裁剪完成存储路径
     * @param x x
     * @param y y
     * @param width 宽度
     * @param height 高度
     */
    public static void cuttingImage(File imageFile, File destFile, int x, int y, int width, int height) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(imageFile));
            BufferedImage bufferedImage = ImageIO.read(bis);
            boolean hasAlpha = bufferedImage.getColorModel().hasAlpha();
            bos = new BufferedOutputStream(new FileOutputStream(destFile));
            BufferedImage targetImage = new BufferedImage(width, height, hasAlpha ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
            targetImage.getGraphics().drawImage(bufferedImage.getSubimage(x, y, width, height), 0, 0, null);
            ImageIO.write(targetImage, hasAlpha ? "png" : "jpg", bos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 图片压缩
     * @param srcImg 源图像文件
     * @param destImg 目标文件
     * @param imgQtty 图像质量[0~1]
     */
    public static void compressImage(File srcImg, File destImg, float imgQtty) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcImg));
            bos = new BufferedOutputStream(new FileOutputStream(destImg));
            BufferedImage bufferedImage = ImageIO.read(srcImg);
            //boolean hasAlpha = bufferedImage.getColorModel().hasAlpha();
            Iterator<ImageWriter> imageWriterIterator = ImageIO.getImageWritersByFormatName("jpeg");
            if (!imageWriterIterator.hasNext()) {
                throw new RuntimeException("ImageWriter for JPEG not found!");
            }
            ImageWriter imageWriter = imageWriterIterator.next();
            imageWriter.setOutput(ImageIO.createImageOutputStream(bos));
            ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
            imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            imageWriteParam.setCompressionQuality(imgQtty);
            imageWriter.write(null, new IIOImage(bufferedImage, null, null), imageWriteParam);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 图像圆角
     * @param srcImg 源文件
     * @param destImg 目标文件
     * @param radius 圆角半径
     */
    public static void cornerImage(File srcImg, File destImg, int radius) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(srcImg));
            bos = new BufferedOutputStream(new FileOutputStream(destImg));
            BufferedImage bufferedImage = ImageIO.read(bis);
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            int cornerRadius = radius < 1 ? width / 4 : radius;
            BufferedImage targetImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = targetImage.createGraphics();
            graphics2D.setComposite(AlphaComposite.Src);
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setColor(Color.WHITE);
            graphics2D.fill(new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius));
            graphics2D.setComposite(AlphaComposite.SrcAtop);
            graphics2D.drawImage(bufferedImage, 0, 0, null);
            graphics2D.dispose();
            ImageIO.write(targetImage, "PNG", bos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        File srcImg = new File("/Users/leye/Documents/1001.jpg");
        File targetImg = new File("/Users/leye/Documents/1001.compress.jpg");
        int[] imgSize = ImageUtils.getImageSizeInfo(srcImg);
        if (null != imgSize) {
//            ImageUtils.resizeImage(srcImg, targetImg, imgSize[0]/2, imgSize[1]/2);
//            ImageUtils.cuttingImage(srcImg, targetImg, 100, 100, 100, 100);
//            ImageUtils.compressImage(srcImg, targetImg, 0.9f);
            ImageUtils.cornerImage(srcImg, targetImg, 100);
            System.out.println("转换完成," + targetImg.getAbsolutePath());
        }
    }
}
