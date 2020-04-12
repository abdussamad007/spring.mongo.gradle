package com.abdus.spring.mongo.gradle.example.common;

import java.util.Base64;

public class Base64EncodeUtil {

  public static  String encodePassword(String passwordText){

    // create a sample String to encode
   // String sample = "India Team will win the Cup";

    // print actual String
    System.out.println("Sample String:\n"
      + passwordText);

    // Encode into Base64 format
    String BasicBase64format
      = Base64.getEncoder()
      .encodeToString(passwordText.getBytes());

    // print encoded String
   // System.out.println("Encoded String:\n"+ BasicBase64format);
    return BasicBase64format;
  }

  public static String decodePassword(String encodedPassword){
    // print encoded String
    //System.out.println("Encoded String:\n" + encoded);

    // decode into String from encoded format
    byte[] actualByte = Base64.getDecoder().decode(encodedPassword);

    String actualString = new String(actualByte);

    // print actual String
    //System.out.println("actual String:\n" + actualString);

    return actualString;
  }

  public static void main(String[] args) {
    String passwordText = "root";
    String encodedPassword = encodePassword(passwordText);
    System.out.println("-----encodedPassword:"+encodedPassword);

    System.out.println("-----encodedPassword:"+decodePassword(encodedPassword));

  }
}
