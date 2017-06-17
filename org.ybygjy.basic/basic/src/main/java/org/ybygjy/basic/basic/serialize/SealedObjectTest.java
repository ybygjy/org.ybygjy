package org.ybygjy.basic.basic.serialize;

import javax.crypto.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by leye on 2017/4/8.
 */
public class SealedObjectTest {
    public static void main(String[] args) {
        try {
            Cipher cipherInst = Cipher.getInstance("DES/CBC/PKCS5Padding");
            Person person = new Person("王", "延成", 1, Gender.MALE);
            DSAPrivateKeySpec bobPubKey = new DSAPrivateKeySpec(new BigInteger("20"), new BigInteger("20"), new BigInteger("30"), new BigInteger("40"));
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            Key key = keyFactory.generatePrivate(bobPubKey);
            cipherInst.init(Cipher.ENCRYPT_MODE, key);
            SealedObject sealedObject = new SealedObject(person, cipherInst);
            Object result = sealedObject.getObject(cipherInst);
            System.out.println(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
