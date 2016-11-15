package com.ironyard.controller.mvc;

import com.ironyard.data.Accounts;
import com.ironyard.data.RiskFreeAccount;
import com.ironyard.data.Transactions;
import com.ironyard.data.User;
import com.ironyard.dto.AccountPager;
import com.ironyard.dto.TransactionPager;
import com.ironyard.repositories.AccountRepository;
import com.ironyard.repositories.TransactionRepository;
import com.ironyard.repositories.UserRepository;
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
    UserRepository userRepo = null;

    @Autowired
    AccountRepository acctRepo = null;

    @Autowired
    TransactionRepository transRepo = null;



    @RequestMapping(value = "riskFree", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request){

        // get current logged in user
        User user = (User)request.getSession().getAttribute("user");

        Long usrId = user.getId();

        // get users accounts
        Set<RiskFreeAccount> riskFree = userRepo.findOne(usrId).getRiskFree();

        // put them in a model
        model.addAttribute("riskFree", riskFree);

        // send them to the home page

        return "/secure/home"; //send to home page or accounts? if home page I need to make a home page.
                                // think about what a 'home page' would look like
    }



    @RequestMapping(value = "personalAccounts", method = RequestMethod.GET)
    public String MyAccounts(Model model, HttpServletRequest request){

        // get current logged in user
        User user = (User)request.getSession().getAttribute("user");

        Long usrId = user.getId();

        // get users accounts
        Set<Accounts> personalAccounts = userRepo.findOne(usrId).getPersonalAccounts();

        // put them in a model
        model.addAttribute("personalAccounts", personalAccounts);

        // send them to the home page

        return "/secure/myaccounts"; //send to account page? if home page I need to make a home page.
        // think about what a 'home page' would look like
    }



    // this is not necessary right now since the main objective is to just get a savings account up and running
    // however it might be wise to structure around the idea of implementing the apps ability to sort other
    // account types for both pre-existing and imported. (e.g. the iPhone app 'Mint')

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String allAccounts(@RequestParam(value = "page", required = false) Integer page,
                              Model model, HttpServletRequest request){

        if(page == null){
            page = 0;
        }
        Sort s = new Sort(Sort.Direction.DESC, "title");
        PageRequest pr = new PageRequest(page, 2, s);
        Page<Accounts> aPageOfAccounts = acctRepo.findAll(pr);

        AccountPager ap = new AccountPager(page, aPageOfAccounts);

        model.addAttribute("all_accounts", aPageOfAccounts.iterator());
        model.addAttribute("account_pager", ap);



        // again send to home page or accounts?
        return "/secure/myaccounts/list_all_accounts";
    }

//______________________________________________________________________________________________________________________



    @RequestMapping(value = "accountHistory", method = RequestMethod.GET)
    public String accountDetails(Model model, HttpServletRequest request){

        // get current logged in user's account
        Accounts acct = (Accounts)request.getSession().getAttribute("account");

        Long actId = acct.getId();

        // get user's account history
        Set<Transactions> accountHistory = acctRepo.findOne(actId).getAccountHistory();


        // put them in a model
        model.addAttribute("accountHistory", accountHistory);

        // send them to the account details page
        return "/secure/myaccounts/details";
    }





    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String allTransactions(@RequestParam(value = "page", required = false) Integer page,
                                  Model model, HttpServletRequest request){

        if(page == null){
            page = 0;
        }
        Sort s = new Sort(Sort.Direction.DESC, "date"); //**************** this needs to sort: DESC by date
        PageRequest pr = new PageRequest(page, 2, s);
        Page<Transactions> aPageOfTransactions = transRepo.findAll(pr);

        TransactionPager tp = new TransactionPager(page, aPageOfTransactions);

        model.addAttribute("all_transactions", aPageOfTransactions.iterator());
        model.addAttribute("transaction_pager", tp);



        // Should I send them to accounts or all the way back to a homepage? (makes since to send them to accounts)
        return "/secure/myaccounts/details/list_all_transactions";


    }

}
