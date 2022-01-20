package cn.i623.myfxutil.util;

import cn.i623.myfxutil.myinterface.StringEncrypt;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaSimple implements StringEncrypt {
    public static final String ARITH = "RSA";
    public PublicKey rsaPublicKey;
    private PrivateKey rsaPrivateKey;

    public RsaSimple() throws NoSuchAlgorithmException {
        // 1.初始化私钥公钥
        initKeyPair(ARITH);
        System.out.println("公钥:" + Base64.encodeBase64String(rsaPublicKey.getEncoded()));
        System.out.println("私钥:" + Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
    }

    public static void main(String[] args) throws Exception {
        new RsaSimple().testRsa("说天晴");
    }

    private static String getString(byte[] result2) {
        return Base64.encodeBase64String(result2);
    }

    public void testRsa(String text) throws Exception {

        // 2.公钥加密
        byte[] result2 = traEncode(text);
        String priviteText = getString(result2);
        System.out.println(priviteText);
        // 3.私钥解密
        byte[] bytes = Base64.decodeBase64(priviteText);
        String resetText = traDecode(bytes);
//        String resetText = traDecode(result2);
        System.out.println(resetText);
    }

    private String traDecode(byte[] result2) throws Exception {
        PrivateKey privateKey5 = KeyFactory.getInstance(ARITH).generatePrivate(new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded()));
        Cipher cipher5 = Cipher.getInstance(ARITH);
        cipher5.init(Cipher.DECRYPT_MODE, privateKey5);
        byte[] result5 = cipher5.doFinal(result2);
        return new String(result5);
    }

    private byte[] traEncode(String text) throws Exception {
        PublicKey publicKey2 = KeyFactory.getInstance(ARITH).generatePublic(new X509EncodedKeySpec(rsaPublicKey.getEncoded()));
        Cipher cipher2 = Cipher.getInstance(ARITH);
        cipher2.init(Cipher.ENCRYPT_MODE, publicKey2);
        byte[] result2 = cipher2.doFinal(text.getBytes());
        System.out.println("公钥加密、私钥解密 ---- 加密:" + getString(result2));
        return result2;
    }

    /*初始化加密方式*/
    private void initKeyPair(String method) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(method);
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.rsaPublicKey = keyPair.getPublic();
        this.rsaPrivateKey = keyPair.getPrivate();
    }

    @Override
    public String encrypt(String text, PublicKey rsaPublicKey) {
        return null;
    }

    @Override
    public String decrypt(String cryptText, PrivateKey rsaPrivateKey) {
        return null;
    }

    @Override
    public String encrypt(String text) throws Exception {
        // 2.公钥加密
        byte[] result2 = traEncode(text);
        String priviteText = getString(result2);
        System.out.println(priviteText);
        return priviteText;
    }

    @Override
    public String decrypt(String cryptText) throws Exception {
        // 3.私钥解密
        byte[] bytes = Base64.decodeBase64(cryptText);
        String resetText = traDecode(bytes);
//        String resetText = traDecode(result2);
        System.out.println(resetText);
        return resetText;
    }
}