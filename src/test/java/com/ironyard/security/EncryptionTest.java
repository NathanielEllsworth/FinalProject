package com.ironyard.security;

import com.ironyard.security.Encryption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;


import static org.junit.Assert.*;

/**
 * Created by nathanielellsworth on 11/12/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EncryptionTest {

    @Test
    public void authenticatedTokenShouldBeValid() throws Exception {
        String token = null;
        try{
            token = Encryption.genToken();
            System.out.println(token);
            Assert.assertTrue(Encryption.isValidKey(token));
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }

    @Test
    public void fakeTokenShouldBeDenied() throws Exception{
        String token = null;
        try{
            token = Encryption.genToken()+"FAKE";
            System.out.println(token);
            Assert.assertFalse(Encryption.isValidKey(token));
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }

}