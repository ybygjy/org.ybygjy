package org.ybygjy.pay.paynow.util;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class DESUtils {

    /**
     * 3des 加密解密工具类
     **/
    public static byte[] UnionDesEncrypt(byte[] key, byte[] data) {
        try {
            KeySpec ks = new DESKeySpec(key);
            SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
            SecretKey ky = kf.generateSecret(ks);
            Cipher c = Cipher.getInstance("DES/ECB/NoPadding");
            c.init(Cipher.ENCRYPT_MODE, ky);
            return c.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * des解密
     * @param key 密钥
     * @param data 密文数据 16进制且长度为16的整数倍
     * @return 明文数据
     */
    public static byte[] UnionDesDecrypt(byte[] key, byte[] data) {
        try {
            KeySpec ks = new DESKeySpec(key);
            SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
            SecretKey ky = kf.generateSecret(ks);
            Cipher c = Cipher.getInstance("DES/ECB/NoPadding");
            c.init(Cipher.DECRYPT_MODE, ky);
            return c.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 3des加密
     * @param key 密钥
     * @param data 明文数据 16进制且长度为16的整数倍不足时补0
     * @return 密文数据
     */
    public static byte[] Union3DesEncrypt(byte[] key, byte[] data) {
        try {
            byte[] k = new byte[24];
            if (key.length == 16) {
                System.arraycopy(key, 0, k, 0, key.length);
                System.arraycopy(key, 0, k, 16, 8);
            } else {
                System.arraycopy(key, 0, k, 0, 24);
            }
            byte[] buff = complementZero(data);
            KeySpec ks = new DESedeKeySpec(k);
            SecretKeyFactory kf = SecretKeyFactory.getInstance("DESede");
            SecretKey ky = kf.generateSecret(ks);
            Cipher c = Cipher.getInstance("DESede/ECB/NoPadding");
            c.init(Cipher.ENCRYPT_MODE, ky);
            return c.doFinal(buff);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 3des解密
     * @param key 密钥
     * @param data 密文数据 16进制且长度为16的整数倍
     * @return 明文数据
     */
    public static byte[] Union3DesDecrypt(byte[] key, byte[] data) {
        try {
            byte[] k = new byte[24];
            if (key.length == 16) {
                System.arraycopy(key, 0, k, 0, key.length);
                System.arraycopy(key, 0, k, 16, 8);
            } else {
                System.arraycopy(key, 0, k, 0, 24);
            }
            KeySpec ks = new DESedeKeySpec(k);
            SecretKeyFactory kf = SecretKeyFactory.getInstance("DESede");
            SecretKey ky = kf.generateSecret(ks);
            Cipher c = Cipher.getInstance("DESede/ECB/NoPadding");
            c.init(Cipher.DECRYPT_MODE, ky);
            return c.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 加密数据
     * @param key 密钥 16进制且长度为16的整数倍
     * @param data 明文数据 16进制且长度为16的整数倍不足时补0
     * @return 密文数据 16进制
     */
    public static String UnionEncryptData(String key, String data) {
        if ((key.length() != 16) && (key.length() != 32) && (key.length() != 48)) {
            return null;
        }
        int lenOfKey;
        lenOfKey = key.length();
        String strEncrypt = "";
        byte sourData[] = hexString2Bytes(data);
        switch (lenOfKey) {
        case 16:
            byte desKey8[] = hexString2Bytes(key);
            byte encrypt8[] = UnionDesEncrypt(desKey8, sourData);
            strEncrypt = bytes2HexString(encrypt8, false);
            break;
        case 32:
            String newKey = key.substring(0, 16);
            newKey = key + newKey;
            byte keyByte16[] = hexString2Bytes(newKey);
            byte encrypt16[] = Union3DesEncrypt(keyByte16, sourData);
            strEncrypt = bytes2HexString(encrypt16, false);
            break;
        case 48:
            byte keyByte24[] = hexString2Bytes(key);
            byte encrypt24[] = Union3DesEncrypt(keyByte24, sourData);
            strEncrypt = bytes2HexString(encrypt24, false);
            break;
        }
        return strEncrypt;
    }

    /**
     * 数据解密
     * @param key 密钥 支持单倍和多倍密钥
     * @param data 密文数据 16进制且长度为16的整数倍
     * @return 明文数据 16进制
     */
    public static String UnionDecryptData(String key, String data) {
        if ((key.length() != 16) && (key.length() != 32) && (key.length() != 48)) {
            return (null);
        }
        int lenOfKey;
        lenOfKey = key.length();
        String strEncrypt = "";
        byte sourData[] = hexString2Bytes(data);
        switch (lenOfKey) {
        case 16:
            byte desKey8[] = hexString2Bytes(key);
            byte encrypt8[] = UnionDesDecrypt(desKey8, sourData);
            strEncrypt = bytes2HexString(encrypt8, false);
            break;
        case 32:
            String newKey = key.substring(0, 16);
            newKey = key + newKey;
            byte keyByte16[] = hexString2Bytes(newKey);
            byte encrypt16[] = Union3DesDecrypt(keyByte16, sourData);
            strEncrypt = bytes2HexString(encrypt16, false);
            break;
        case 48:
            byte keyByte24[] = hexString2Bytes(key);
            byte encrypt24[] = Union3DesDecrypt(keyByte24, sourData);
            strEncrypt = bytes2HexString(encrypt24, false);
            break;
        }
        return strEncrypt;
    }
    private static byte[] hexString2Bytes(String str) {
        Hex hexInst = new Hex("utf-8");
        byte[] rtnBytes = null;
        try {
            rtnBytes = hexInst.decode(str.getBytes(hexInst.getCharset()));
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return rtnBytes;
    }
    private static String bytes2HexString(byte[] bytes, boolean toLowerCase) {
        return new String(Hex.encodeHex(bytes, toLowerCase));
    }
    /**
     * 字节补齐
     * @param bytes
     * @return
     */
    private static byte[] complementZero(byte[] b) {
        int len = b.length;
        if (b.length % 8 != 0) {
            len = b.length - b.length % 8 + 8;
        } else {
            return b;
        }
        byte[] needData = null;
        needData = new byte[len];
        for (int i = 0; i < len; i++) {
            needData[i] = 0x00;
        }
        System.arraycopy(b, 0, needData, 0, b.length);
        return needData;
    }
}
