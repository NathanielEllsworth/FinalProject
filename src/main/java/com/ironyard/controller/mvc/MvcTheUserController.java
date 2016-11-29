package com.ironyard.controller.mvc;

import com.ironyard.data.Account;
import com.ironyard.data.TheUser;
import com.ironyard.repositories.AccountRepository;
import com.ironyard.repositories.TheUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;

/**
 * This is where the User can add, delete, create and rename their different Account
 *
 * Created by nathanielellsworth on 11/4/16.
 */
@Controller
@RequestMapping(path = "/mvc/secure/user")
public class MvcTheUserController {

    @Autowired
    TheUserRepository userRepository = null;

    @Autowired
    AccountRepository accountRepository = null;

    @RequestMapping(value = "account/savings/delete", method = RequestMethod.GET)
    public String deleteTransaction(@RequestParam("id") Long id, HttpServletRequest request){
//        // get current logged in user, need to case (TheUser) to proper type
//        TheUser user = (TheUser)request.getSession().getAttribute("user");
//
//        // refetch user from db
//        TheUser fetchedUser = userRepository.findOne(user.getId());
//
//        // find this account id in user favorites and remove it
//        Account accountToRemove = null;
//        for(Account tmp: fetchedUser.getOtherAccounts()){
//            if(tmp.getId() == id){
//                // this is the account to remove
//                accountToRemove = tmp;
//            }
//        }
//
//        if(accountToRemove != null) {
//            fetchedUser.getOtherAccounts().remove(accountToRemove);
//        }
//        userRepository.save(fetchedUser);
        accountRepository.delete(accountRepository.findOne(id));
        // send them to the home page
        return "redirect:/mvc/secure/account/savings";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "account/savings/add", method = RequestMethod.POST)
    public String addTransaction(@RequestParam("date") Date date,
                                 @RequestParam("type") String type,
                                 @RequestParam("description") String description,
                                 @RequestParam("debit") double debit,
                                 @RequestParam("credit") double credit,
                                 @RequestParam("term") String term,
                                 @RequestParam("tBillReturn") double tBillReturn,
                                 @RequestParam("bankReturn") double bankReturn,
                                 @RequestParam("returnIncrease") double returnIncrease,
                                 @RequestParam("postedBalance") double postedBalance,
                                 @RequestParam("availableBalance") double availableBalance,HttpServletRequest request){

        TheUser user = (TheUser)request.getSession().getAttribute("user");



        Account tmpA = new  Account();
        tmpA.setUserId(user.getId());
        // set properties
        tmpA.setDate(date);
        tmpA.setType(type);
        tmpA.setDescription(description);
        tmpA.setDebit(debit);
        tmpA.setCredit(credit);
        tmpA.setTerm(term);
        tmpA.settBillRate(tBillReturn);
        tmpA.setBankRate(bankReturn);
        tmpA.setRateDifference(returnIncrease);
        tmpA.setPostedBalance(postedBalance);
        tmpA.setAvailableBalance(availableBalance);
        accountRepository.save(tmpA);


//        // get current logged in user, need to case (TheUser) to proper type
//
//
//        // refetch user from db
//        TheUser fetchedUser = userRepository.findOne(user.getId());
//
//        Account accountToAddToSavings = accountRepository.findOne(id);
//
//        if(fetchedUser.getOtherAccounts() == null){
//            fetchedUser.setOtherAccounts(new HashSet<>());
//        }
//        fetchedUser.getOtherAccounts().add(accountToAddToSavings);

//        userRepository.save(fetchedUser);

        // send them to the home page
        return "redirect:/mvc/secure/account/savings";
    }
}
