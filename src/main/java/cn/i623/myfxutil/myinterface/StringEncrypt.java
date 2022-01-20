package cn.i623.myfxutil.myinterface;

import java.security.PrivateKey;
import java.security.PublicKey;

/*字符串加密解密*/
public interface StringEncrypt {
    /*加密*/
    String encrypt(String text, PublicKey rsaPublicKey);

    /*解密*/
    String decrypt(String cryptText, PrivateKey rsaPrivateKey);

    /*加密*/
    String encrypt(String text) throws Exception;

    /*解密*/
    String decrypt(String cryptText) throws Exception;

}