package com.ironyard.controller.mvc;

import com.ironyard.LoggingRequestInterceptor;
import com.ironyard.dto.TreasuryBills;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nathanielellsworth on 11/11/16.
 */
@Controller
@RequestMapping(path = "/mvc/secure/account")
public class MvcTreasuryBillsController {

//    @RequestMapping(value = "/tbills", method = RequestMethod.GET)
//    public String list(Map<String, Object> model) {
//
//        RestTemplate restTemplate = new RestTemplate();
//        TreasuryBills[] tbills = restTemplate.getForObject("http://www.treasurydirect.gov/TA_WS/securities/auctioned?format=json&type=Bill" , TreasuryBills[].class);
//
//        // GET RID OF ALL THE %20 IN THE URL's!! (those represent blank spaces)
//
//        model.put("tbills", tbills);
//        //Back to home
//        return "/secure/list_all_accounts";
//
//
//    }

//    @RequestMapping(value = "/tbills/all", method = RequestMethod.GET)
//    public String list(Map<String, Object> model, @RequestParam(value = "dropdown", required = false) String aDate) {
//
//        RestTemplate restTemplate = new RestTemplate();
//        TreasuryBills[] Date = restTemplate.getForObject( aDate + "http://www.treasurydirect.gov/TA_WS/securities/auctioned?format=json&type=Bill" , TreasuryBills[].class);
//
//        // GET RID OF ALL THE %20 IN THE URL's!! (those represent blank spaces)
//
//        model.put("date", Date);
//
//        RestTemplate restTemplate2 = new RestTemplate();
//        TreasuryBills[] tbills = restTemplate2.getForObject("http://www.treasurydirect.gov/TA_WS/securities/auctioned?format=json&type=Bill" , TreasuryBills[].class);
//
//        // GET RID OF ALL THE %20 IN THE URL's!! (those represent blank spaces)
//
//        model.put("tbills", tbills);
//        //Back to home
//        return "/secure/list_all_accounts";
//
//
//    }

}
