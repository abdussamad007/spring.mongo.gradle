package com.abdus.spring.mongo.gradle.example.common;

import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

public class RSAUtil {
  static String password = "root";

  public static void main(String[] args) throws Exception {
    /*// Get an instance of the RSA key generator
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
    keyPairGenerator.initialize(4096);

    // Generate the KeyPair
    KeyPair keyPair = keyPairGenerator.generateKeyPair();

    // Get the public and private key
    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();

    // Get the RSAPublicKeySpec and RSAPrivateKeySpec
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
    RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);

    // Saving the Key to the file
    saveKeyToFile("public.key", publicKeySpec.getModulus(), publicKeySpec.getPublicExponent());
    saveKeyToFile("private.key", privateKeySpec.getModulus(), privateKeySpec.getPrivateExponent());
*/
    System.out.println("Original Text  : " + password);

    // Encryption
    /*byte[] cipherTextArray = encrypt(password, "public.key");
    String encryptedText = Base64.getEncoder().encodeToString(cipherTextArray);
    System.out.println("Encrypted Text : " + encryptedText);*/

    // Decryption
    String encryptedText = "hCdNa0j/dZ+z8p4l5hXsKoZ2Coay3+bKWl4+T4EtP1rDRmFMxUiK5Jv74jibHJbsvxykvKlAACfV2dwNl6i+08Fr/N9brM9Qe7Fjx9GObVv8286ddkjZczpcRnnH3nmg6VbKMrGNS2qISp2dULYkWiiF1XVVARcsJ0BdyA8tiXD2b0lZSqYPFS2VXbC8l78ku1nRNxsIPi6ej8s7NswxqJJIuPleBoRxVjh1heLr0VhsYGW1fubvur5dmXXYGOMyLPg5fJri5Scp7SGhUt2mi7sZe5pgM7ICYyDOq1zwuhtQuQD7GdY3iV5LPXbssy1PWZQeJsxVdOS4lTwlJyEoPvyX3R3UfIn5/mpVDk1yQYhQotLD1BSR7DeI1W8BDjw52v6+ehxfo84SdAguVOmSa3mzWs8ZkE8x0iGJK4YVG+EdSf7J1SLlCn7Wm12ardB/AKOixPRwYKfbNou/Pn3hYiuVejPsfYaIKvv0Tv/D0swaJuwXoYlN4z6dcykIioT8thyG7DtdxiOSoFBzCE51u+DwPjth+of3gX5URJqDInHj6Mui+xgy/LGAtcwCQ9X3zHF6WcL4kcafYfprGwSv2oD3KQT0zf1jeA1b6L7Z9XwcE/B35O26wpKDglquFVKQtg71AKyW1QZgMnVFtfr4rX+G5ug/Jpyfr8rfox8/b2I=";

    byte[] cipherTextArray = Base64.getDecoder().decode(encryptedText);

    String decryptedText = decrypt(cipherTextArray, "private.key");
    System.out.println("DeCrypted Text : " + decryptedText);

  }

  public static void saveKeyToFile(String fileName, BigInteger modulus, BigInteger exponent) throws IOException {
    ObjectOutputStream ObjOutputStream = new ObjectOutputStream(
      new BufferedOutputStream(new FileOutputStream(fileName)));
    try {
      ObjOutputStream.writeObject(modulus);
      ObjOutputStream.writeObject(exponent);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      ObjOutputStream.close();
    }
  }

  public static Key readKeyFromFile(String keyFileName) throws IOException {
    Key key = null;
    InputStream inputStream = new FileInputStream(keyFileName);
    ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
    try {
      BigInteger modulus = (BigInteger) objectInputStream.readObject();
      BigInteger exponent = (BigInteger) objectInputStream.readObject();
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      if (keyFileName.startsWith("public"))
        key = keyFactory.generatePublic(new RSAPublicKeySpec(modulus, exponent));
      else
        key = keyFactory.generatePrivate(new RSAPrivateKeySpec(modulus, exponent));

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      objectInputStream.close();
    }
    return key;
  }

  public static byte[] encrypt(String plainText, String fileName) throws Exception {
    Key publicKey = readKeyFromFile("public.key");

    // Get Cipher Instance
    Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");

    // Initialize Cipher for ENCRYPT_MODE
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);

    // Perform Encryption
    byte[] cipherText = cipher.doFinal(plainText.getBytes());

    return cipherText;
  }

  public static String decrypt(byte[] cipherTextArray, String fileName) throws Exception {
    Key privateKey = readKeyFromFile("private.key");

    // Get Cipher Instance
    Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");

    // Initialize Cipher for DECRYPT_MODE
    cipher.init(Cipher.DECRYPT_MODE, privateKey);

    // Perform Decryption
    byte[] decryptedTextArray = cipher.doFinal(cipherTextArray);

    return new String(decryptedTextArray);
  }

}
