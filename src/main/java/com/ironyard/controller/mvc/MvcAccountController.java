package com.ironyard.controller.mvc;

import com.ironyard.data.Account;
import com.ironyard.data.TheUser;
import com.ironyard.dto.AccountPager;
import com.ironyard.dto.TreasuryBills;
import com.ironyard.repositories.AccountRepository;
import com.ironyard.repositories.TheUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;

/**
 * This is where the User can view their transaction history data for each account, Account Balance, make changes, add memos, and
 * possibly move money from one account to another
 *
 *
 * Created by nathanielellsworth on 11/5/16.
 */

@Controller
@RequestMapping(path = "/mvc/secure/account")
public class MvcAccountController {



    @Autowired
    TheUserRepository userRepository = null;
    @Autowired
    AccountRepository accountRepository = null;


    /**
     *
     * @param page
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String jsonDataTBills(@RequestParam(value = "page", required = false) Integer page,
                                 Model model, HttpServletRequest request){

         //send them to the home page
        RestTemplate restTemplate = new RestTemplate();
        TreasuryBills[] tbills = restTemplate.getForObject("http://www.treasurydirect.gov/TA_WS/securities/auctioned?format=json&type=Bill" , TreasuryBills[].class);


        System.out.println("size of tbills" + tbills.length);
        //model.addAttribute("treasury_pager", ap);
        model.addAttribute("aTBill", tbills);
        return "/secure/treasury";

    }

    /**
     *
     * @param page
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "savings", method = RequestMethod.GET)
    public String home(@RequestParam(value = "page", required = false) Integer page,
                       Model model, HttpServletRequest request){

        TheUser user = (TheUser)request.getSession().getAttribute("user");

        Long usrId = user.getId();

        // get users savings

        if(page == null){
            page = 0;
        }
        Sort s = new Sort(Sort.Direction.DESC, "date");
        PageRequest pr = new PageRequest(page, 10, s);
        Page<Account> aPageOfTransactions =  accountRepository.findByUserId(usrId,pr);

        AccountPager ap = new AccountPager(page ,aPageOfTransactions);

        // put them in a model
        model.addAttribute("all_transactions", aPageOfTransactions.iterator());
        model.addAttribute("account_pager", ap);

        // send them to the home page


        return "/secure/home";
    }

}
