package com.ironyard.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nathanielellsworth on 11/4/16.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class TreasuryBillsTest {


    // quick status code test

    @Test
    public void addQuickTreasuryBillTest() throws IOException{

        URL url = new URL("http://www.treasurydirect.gov/TA_WS/securities/auctioned?format=json&type=Bill");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        int statusCode = http.getResponseCode();
    }


}