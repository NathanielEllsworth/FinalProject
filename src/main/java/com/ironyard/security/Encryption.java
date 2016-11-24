package com.ironyard.security;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by nathanielellsworth on 11/4/16.
 */
public class Encryption {


    private static String SECRET = "x";

    private static boolean keyMatches(String checkKey){
        return checkKey.equalsIgnoreCase(SECRET);
    }

    /**
     * This will generate and encrypted token that can be used with all JSON APIs.
     * Ensure the token is set as a header parameter with a key of 'x-authorization-key'
     * <p>Example: "asldjalsdjfasdfjoasdjfadsfj123"</p>
     *
     * @return a string object with the encrypted token
     * @throws Throwable
     */


    public static String genToken() throws Throwable{

        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        // user secret message
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());
        String secretMessage = String.format("%s:%s", date, SECRET);

        //encrypt message
        byte[] hasil = cipher.doFinal(secretMessage.getBytes());
        return new BASE64Encoder().encode(hasil);
    }

    public static String decrypt(String decrypting){
        String decrypted = null;

        try{
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] hasil = cipher.doFinal(new BASE64Decoder().decodeBuffer(decrypting));
            decrypted = new String(hasil);

        }catch(Throwable t){
            //ignore
        }
        return decrypted;
    }

    public static boolean isValidKey(String key) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar oneYearAgo = Calendar.getInstance();
        oneYearAgo.add(Calendar.MONTH, -12);
        boolean authorized = false;
        if(key != null){
            String decrypted = null;
            try{
                decrypted = decrypt(key);
            }catch (Throwable throwable){
                throwable.printStackTrace();
            }
            if(decrypted != null){
                StringTokenizer st = new StringTokenizer(decrypted, ":");
                if(st.countTokens() == 2){
                    try{
                        Date dayOfToken = dateFormat.parse(st.nextToken());
                        //must be within a year
                        authorized = dayOfToken.after(oneYearAgo.getTime());
                        // second part of token should match our key
                        authorized = authorized && SECRET.equals(st.nextToken());
                    }catch(Throwable t){
                        t.printStackTrace();
                    }
                }
            }
        }
        return authorized;
    }
}
