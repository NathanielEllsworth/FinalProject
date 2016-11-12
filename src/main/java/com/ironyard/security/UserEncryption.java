package com.ironyard.security;


import javax.crypto.spec.SecretKeySpec;
/**
 * Created by nathanielellsworth on 11/4/16.
 */
public class UserEncryption {


    private static String SECRET = "x";

    private static boolean keyMatches(String checkKey){
        return checkKey.equalsIgnoreCase(SECRET);
    }



    public static String genToken() throws Throwable{

        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET.getBytes(), "Blowfish");

    }



}
