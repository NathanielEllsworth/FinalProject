package com.ironyard.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static jdk.nashorn.internal.objects.NativeString.substring;
import static org.junit.Assert.*;

/**
 * Created by nathanielellsworth on 11/4/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TreasuryBillsTest {



    @Test
    public void addTreasuryBills(){
        RestTemplate restTemplate = new RestTemplate();
        TreasuryBills[] bills = restTemplate.getForObject("http://www.treasurydirect.gov/TA_WS/securities/auctioned?format=json&type=Bill", TreasuryBills[].class);
        for(int i=0; i<bills.length; i++){
            String dec = bills[i].getSecurityTerm();
            if(dec.length()>250){
                dec = dec.substring(0,250);
            }

        }

    }

}