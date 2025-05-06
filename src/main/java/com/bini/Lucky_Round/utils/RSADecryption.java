package com.bini.Lucky_Round.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSADecryption {

    public static String decryptRSA(String cipherString, String keyPath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        Path privateKeyPath = Paths.get("private.der");
//        File file = privateKeyPath.toFile();
//        File file = ResourceUtils.getFile(keyPath);

        ClassPathResource cpr = new ClassPathResource(keyPath);

        byte[] privateKeyBytes = FileCopyUtils.copyToByteArray(cpr.getInputStream());

//        byte[] privateKeyBytes = Files.readAllBytes(file.toPath());
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));


        byte[] decoded = Base64.getDecoder().decode(cipherString);
        Cipher cipher1 = Cipher.getInstance("RSA");
        cipher1.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decStr = cipher1.doFinal(decoded);
        return new String(decStr, StandardCharsets.UTF_8);

    }
    public static String decryptRSA2(String cipherString, String keyPath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        Path privateKeyPath = Paths.get("private.der");
//        File file = privateKeyPath.toFile();
//        File file = ResourceUtils.getFile(keyPath);

        ClassPathResource cpr = new ClassPathResource(keyPath);

        byte[] privateKeyBytes = FileCopyUtils.copyToByteArray(cpr.getInputStream());

//        byte[] privateKeyBytes = Files.readAllBytes(file.toPath());
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));


        byte[] decoded = Base64.getDecoder().decode(cipherString);
        Cipher cipher1 = Cipher.getInstance("RSA");
        cipher1.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decStr = cipher1.doFinal(decoded);
        return byteArrayToBase64(decStr);

    }
    private static String byteArrayToBase64(final byte[] byteArray) {
        return new String(Base64.getEncoder().encode(byteArray), StandardCharsets.UTF_8);
    }
}
