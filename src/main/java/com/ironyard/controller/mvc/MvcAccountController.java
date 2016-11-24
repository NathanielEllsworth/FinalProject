package com.ironyard.controller.mvc;

import com.ironyard.data.Account;
import com.ironyard.data.TheUser;
import com.ironyard.dto.AccountPager;
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
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * This is where the User can view their transaction history data for each account, Account Balance, make changes, add memos, and
 * possibly move money from one account to another
 *
 *
 * Created by nathanielellsworth on 11/5/16.
 */
@Controller
@RequestMapping(path = "/mvc/secure/accounts")
public class MvcAccountController {


    @Autowired
    TheUserRepository userRepository = null;

    @Autowired
    AccountRepository accountRepository = null;




    @RequestMapping(value = "savings", method = RequestMethod.GET)
    public String home(@RequestParam(value = "page", required = false) Integer page,
                       Model model, HttpServletRequest request){

        if(page == null){
            page = 0;
        }
        Sort s = new Sort(Sort.Direction.DESC, "date");
        PageRequest pr = new PageRequest(page, 13, s);
        Page<Account> aPageOfTransactions =  accountRepository.findAll(pr);

        AccountPager ap = new AccountPager(page ,aPageOfTransactions);

        // put them in a model
        model.addAttribute("all_transactions", aPageOfTransactions.iterator());
        model.addAttribute("account_pager", ap);

        // send them to the home page
        return "/secure/home";
    }


    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String OtherAccounts(Model model, HttpServletRequest request){
        // get current logged in user, need to case (TheUser) to proper type
        TheUser user = (TheUser)request.getSession().getAttribute("user");

        Long usrId = user.getId();

        // get users savings
        Set<Account> all = userRepository.findOne(usrId).getOtherAccounts();


        model.addAttribute("all", all);

        // send them to the home page
        return "/secure/list_all_accounts";
    }


}
