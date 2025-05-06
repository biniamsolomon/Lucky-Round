package com.bini.Lucky_Round.utils;


public class G2DecryptService {

    /*
    *
    * Single decrypt the supplied double encrypted M-PESA PIN and Secret Key
    * */
    public static String decrypt(String encryptedPIN, String encryptedAESSecretKEY) {
        try { /*
            Get the decrypted secret key first
        */
            String secretKey = RSADecryption.decryptRSA(encryptedAESSecretKEY, "private.der");
        /*
            do AES description with the secret key and the cypher PIN
        */
            String g2decrypted = AESDecryptionUtil.decrypt(encryptedPIN, secretKey);

            return g2decrypted;
        } catch (Exception e) {
            return "-403";
        }
    }
}
