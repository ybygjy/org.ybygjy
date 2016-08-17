package org.ybygjy.pay.paynow.util;

import java.nio.charset.Charset;

import net.iharder.Base64;

/**
 * 报文加解密工具类
 */
public class EncryDecryUtils {
    /**
     * 对数据进行base64加密
     * @param str
     * @return
     */
    public static String base64Encrypt(String str) {
        return Base64.encodeBytes(str.getBytes(Charset.forName("utf-8")));
    }

    /**
     * 对数据进行base64解密
     * @param str
     * @return
     */
    public static String base64Decrypt(String str) {
        String result = null;
        if (str != null) {
            try {
                result = new String(Base64.decode(str), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 给定数据 先用base64解密 再用3Des解密
     * @param key
     * @param data
     * @return
     */
    public static String decryptFromBase64DES(String key, String data) {
        String result = null;
        byte[] keyByte = key.getBytes();
        byte[] base64Byte = org.apache.commons.codec.binary.Base64.decodeBase64(data);
        try {
            result = new String(DESUtils.Union3DesDecrypt(keyByte, base64Byte), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 给定数据，用3des加密 再用base64加密
     * @param key
     * @param data
     * @return
     */
    public static String encryptFromDESBase64(String key, String data) {
        String result = null;
        result = Base64.encodeBytes(DESUtils.Union3DesEncrypt(key.getBytes(), data.getBytes()));
        return result;
    }
    public static String md5(String str) {
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(str.getBytes(Charset.forName("utf-8")));
    }
}
