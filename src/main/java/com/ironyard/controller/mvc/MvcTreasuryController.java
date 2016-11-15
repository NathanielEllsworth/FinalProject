package com.ironyard.controller.mvc;

import com.ironyard.LoggingRequestInterceptor;
import com.ironyard.dto.TreasuryBills;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nathanielellsworth on 11/11/16.
 */
@Controller
public class MvcTreasuryController {
    @RequestMapping(value = "mvc/secure/account/savings/treasurybills", method = RequestMethod.GET)
    public String list(Map<String, Object> model) {

        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        TreasuryBills[] tBills = restTemplate.getForObject("http://www.treasurydirect.gov/TA_WS/securities/auctioned?format=json&type=Bill" , TreasuryBills[].class);

        // GET RID OF ALL THE %20 IN THE URL's!! (those represent blank spaces)

        model.put("TreasuryBills", tBills);
        return "home";


    }
}
