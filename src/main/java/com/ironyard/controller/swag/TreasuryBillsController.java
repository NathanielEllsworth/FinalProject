package com.ironyard.controller.swag;

import com.ironyard.dto.TreasuryBills;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Drag and drop!
 *
 * Created by nathanielellsworth on 11/4/16.
 */
@RestController
@RequestMapping(path = "/rest/treasuryBills")
public class TreasuryBillsController {


    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/mvc/treasury", method = RequestMethod.GET)
    public Iterable<TreasuryBills> list(@RequestParam(value = "filter", required = false) String filter) {
        log.debug("Request to list matches played.");
        RestTemplate restTemplate = new RestTemplate();
        TreasuryBills[] matches = restTemplate.getForObject("http://treasurydirect.gov/TA_WS/securities/auctioned?format=json&type=Bill", TreasuryBills[].class);
        log.info(matches.toString());
        log.debug("Fetch Treasury Bills complete.");
        List<TreasuryBills> foundAllList = Arrays.asList(matches);
        List<TreasuryBills> filteredList = new ArrayList<>();


        // only return matches that start with parameter name
        if (filter != null) {
            // filter the list
            for (TreasuryBills aMatch : foundAllList) {
                if (aMatch.getSecurityTerm().startsWith(filter)) {
                    filteredList.add(aMatch);
                }
            }
        } else {
            // just return all
            filteredList = foundAllList;
        }
        return filteredList;

    }
}
