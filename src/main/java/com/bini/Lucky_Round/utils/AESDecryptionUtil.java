package com.bini.Lucky_Round.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESDecryptionUtil {

    public static String decrypt(String cText, String secretKeyString) throws Exception {

//        byte[] decodedKey = secretKeyString.getBytes(StandardCharsets.UTF_8);
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyString.getBytes());




        // rebuild key using SecretKeySpec
        SecretKey secret = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = generateIv();
        cipher.init(Cipher.DECRYPT_MODE, secret, iv);
        byte[] decodedCypher = Base64.getDecoder()
                .decode(cText);
        byte[] plainText = cipher.doFinal(decodedCypher);
        return new String(plainText);

    }

    public static IvParameterSpec generateIv() {
//        byte[] iv = new byte[16];
//        new SecureRandom().nextBytes(iv);
//        return new IvParameterSpec(iv);

        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        return ivspec;
    }
}
