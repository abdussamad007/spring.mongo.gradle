package com.abdus.spring.mongo.gradle.example.common;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

public class AESUtil {

  private static String generateKey() throws NoSuchAlgorithmException {
    int keyBiteSize = 256;

  //  This class provides a cryptographically strong random number generator (RNG).
   //   A cryptographically strong random number minimally complies with the statistical random number generator
    //   tests specified in FIPS 140-2, Security Requirements for Cryptographic Modules, section 4.9.1.
    //   Additionally, SecureRandom must produce non-deterministic output.
    //   Therefore any seed material passed to a SecureRandom object must be unpredictable,
    //   and all SecureRandom output sequences must be cryptographically strong,
    //   as described in RFC 1750: Randomness Recommendations for Security.
    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
    SecureRandom secureRandom = new SecureRandom();
    keyGenerator.init(keyBiteSize, secureRandom);
    SecretKey secretKey = keyGenerator.generateKey();

    String encodedKey = Base64.getEncoder().encodeToString(
      secretKey.getEncoded());
    System.out.println(encodedKey);

    SecretKey salt = keyGenerator.generateKey();
    String encodedSalt = Base64.getEncoder().encodeToString(
      salt.getEncoded());
    System.out.println(encodedSalt);

 return null;
  }

   public static final String secretKey = "ghWL3ASezuCwL5GnYsQV7r5IdiSC0vaBzY9U0ncSUnE=";
  static final String salt = "Ivwhbmny/8moVuJNUnFGxi3n/0SW9RSOBYEQ4CZikuk=";

  public static String encrypt(String strToEncrypt, String secret)
  {
    try
    {
      byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
      IvParameterSpec ivspec = new IvParameterSpec(iv);

      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
      return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }
    catch (Exception e)
    {
      System.out.println("Error while encrypting: " + e.toString());
    }
    return null;
  }

  public static String decrypt(String strToDecrypt, String secret) {
    try
    {
      byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
      IvParameterSpec ivspec = new IvParameterSpec(iv);

      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }
    catch (Exception e) {
      System.out.println("Error while decrypting: " + e.toString());
    }
    return null;
  }


  public static void main(String[] args) {
    /*try {
      generateKey();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }*/
    String originalString = "root";

    String encryptedString = AESUtil.encrypt(originalString, secretKey) ;
    String decryptedString = AESUtil.decrypt(encryptedString, secretKey) ;

    System.out.println("original password:" +originalString);
    System.out.println("encrypted-password:" + encryptedString);
    System.out.println("decrypted password" + decryptedString);

  }
}
