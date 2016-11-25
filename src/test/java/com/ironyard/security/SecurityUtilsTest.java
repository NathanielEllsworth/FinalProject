package com.ironyard.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert;

/**
 * Created by nathanielellsworth on 11/12/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SecurityUtilsTest {

    @Test
    public void authenticatedTokenShouldBeValid() throws Exception {
        String token = null;
        try{
            token = SecurityUtils.genToken();
            System.out.println(token);
            Assert.assertTrue(SecurityUtils.isValidKey(token));
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }

    @Test
    public void fakeTokenShouldBeDenied() throws Exception{
        String token = null;
        try{
            token = SecurityUtils.genToken()+"FAKE";
            System.out.println(token);
            Assert.assertFalse(SecurityUtils.isValidKey(token));
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }

}