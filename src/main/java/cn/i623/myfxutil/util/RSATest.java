package cn.i623.myfxutil.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSATest {
    public static final String src = "落霞与孤鹜齐飞，秋水共长天一色";

    public static void main(String[] args) throws Exception {
        jdkRSA();
    }

    // jdk实现：
    public static void jdkRSA() throws Exception {
        // 1.初始化发送方密钥
        KeyPair keyPair = initKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
//            System.out.println("Public Key:" + Base64.encodeBase64String(rsaPublicKey.getEncoded()));
//            System.out.println("Private Key:" + Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
//        atransfer(rsaPublicKey, rsaPrivateKey);
        transfer(rsaPublicKey, rsaPrivateKey);

    }

    private static KeyPair initKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    private static void transfer(RSAPublicKey rsaPublicKey, RSAPrivateKey rsaPrivateKey) throws Exception {
        // 4.公钥加密、私钥解密 ---- 加密
        byte[] result2 = traEncode(rsaPublicKey);

        // 5.私钥解密、公钥加密 ---- 解密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        KeyFactory keyFactory5 = KeyFactory.getInstance("RSA");
        PrivateKey privateKey5 = keyFactory5.generatePrivate(pkcs8EncodedKeySpec5);
        Cipher cipher5 = Cipher.getInstance("RSA");
        cipher5.init(Cipher.DECRYPT_MODE, privateKey5);
        byte[] result5 = cipher5.doFinal(result2);
        System.out.println("公钥加密、私钥解密 ---- 解密:" + new String(result5));
    }

    private static byte[] traEncode(RSAPublicKey rsaPublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        KeyFactory keyFactory2 = KeyFactory.getInstance("RSA");
        PublicKey publicKey2 = keyFactory2.generatePublic(x509EncodedKeySpec2);
        Cipher cipher2 = Cipher.getInstance("RSA");
        cipher2.init(Cipher.ENCRYPT_MODE, publicKey2);
        byte[] result2 = cipher2.doFinal(src.getBytes());
        System.out.println("公钥加密、私钥解密 ---- 加密:" + Base64.encodeBase64String(result2));
        return result2;
    }

    private static void atransfer(RSAPublicKey rsaPublicKey, RSAPrivateKey rsaPrivateKey) throws Exception {
        // 2.私钥加密、公钥解密 ---- 加密
        byte[] result = aEncode(rsaPrivateKey);

        // 3.私钥加密、公钥解密 ---- 解密
        aDecode(rsaPublicKey, result);
    }

    private static byte[] aEncode(RSAPrivateKey rsaPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(src.getBytes());
        System.out.println("私钥加密、公钥解密 ---- 加密:" + Base64.encodeBase64String(result));
        return result;
    }

    private static void aDecode(RSAPublicKey rsaPublicKey, byte[] result) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher;
        KeyFactory keyFactory;
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        result = cipher.doFinal(result);
        System.out.println("私钥加密、公钥解密 ---- 解密:" + new String(result));
    }
}