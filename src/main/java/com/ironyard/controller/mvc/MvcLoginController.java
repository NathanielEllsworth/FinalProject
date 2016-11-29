package com.ironyard.controller.mvc;

import com.ironyard.data.Permission;
import com.ironyard.data.TheUser;
import com.ironyard.dto.AccountPager;
import com.ironyard.dto.TreasuryBills;
import com.ironyard.repositories.TheUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Did a lot of JSON api work so some framework is here. Compiling project from the User's point of view, Implementing
 * login page and security.
 *
 * Created by nathanielellsworth on 11/12/16.
 */
@Controller
@RequestMapping(path = "/mvc/open")
public class MvcLoginController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TheUserRepository userRepository;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "password", required = false) String password,
                        @RequestParam(value = "username", required = false) String username,
                        HttpServletRequest request) {
        log.info("Login attempt by:"+username);
        String destination = "open/login";
        TheUser found = userRepository.findByUsernameAndPassword(username, password);
        if(found != null){
            request.getSession().setAttribute("user",found);
            // add permissions to session as well
            HashMap<String, String> permsForThisUser = new HashMap<>();
            for(Permission p: found.getAbilities()){
                permsForThisUser.put(p.getKey(), p.getKey());
            }
            request.getSession().setAttribute("user_loggedin_perms", permsForThisUser);

            // on success we send them to the favs controller to fetch fav movies
            destination = "redirect:/mvc/secure/account/savings";
            log.info("found user:"+found.getId());
        }
        log.info("Login attempt result:"+destination);
        return destination;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        String destination = "/open/login";
        TheUser found = (TheUser) request.getSession().getAttribute("user");
        if(found != null) {
            log.info("Logging out user with id:" + found.getId());
        }
        request.getSession().invalidate();
        return destination;
    }


//    @RequestMapping(value = "all", method = RequestMethod.GET)
//    public String OtherAccounts(@RequestParam(value = "page", required = false) Integer page,
//                                Model model, HttpServletRequest request){
//
//
//        // send them to the home page
//        RestTemplate restTemplate = new RestTemplate();
//        TreasuryBills[] tbills = restTemplate.getForObject("http://www.treasurydirect.gov/TA_WS/securities/auctioned?format=json&type=Bill" , TreasuryBills[].class);
//
//        if(page == null){
//            page = 0;
//        }
//        Sort s = new Sort(Sort.Direction.DESC, "issueDate");
//        PageRequest pr = new PageRequest(page, 10, s);
//        Page<TreasuryBills> aPageOfTreasuryBills = treasuryRepository.findAll(pr);
//
//        //Page<TreasuryBills> aPageOfTreasuryBills = JsonPath.from("http://www.treasurydirect.gov/TA_WS/securities/auctioned?format=json&type=Bill").get("issueDate[0]");
//        AccountPager ap = new AccountPager(page, aPageOfTreasuryBills);
//
//
//        System.out.println("size of tbills" + tbills.length);
//        model.addAttribute("treasury_pager", ap);
//        model.addAttribute("aTBill", tbills);
//        return "/secure/treasury";
//    }


}
