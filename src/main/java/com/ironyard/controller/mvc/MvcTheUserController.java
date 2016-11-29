package com.ironyard.controller.mvc;

import com.ironyard.data.Account;
import com.ironyard.data.TheUser;
import com.ironyard.repositories.AccountRepository;
import com.ironyard.repositories.TheUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

/**
 * This is where the User can add, delete, create and rename their different Account
 *
 * Created by nathanielellsworth on 11/4/16.
 */
@Controller
@RequestMapping(path = "/mvc/secure/account/savings")
public class MvcTheUserController {

    @Autowired
    TheUserRepository userRepository = null;

    @Autowired
    AccountRepository accountRepository = null;

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteSavings(@RequestParam("id") Long id, HttpServletRequest request){
        // get current logged in user, need to case (TheUser) to proper type
        TheUser user = (TheUser)request.getSession().getAttribute("user");

        // refetch user from db
        TheUser fetchedUser = userRepository.findOne(user.getId());

        // find this account id in user favorites and remove it
        Account accountToRemove = null;
        for(Account tmp: fetchedUser.getOtherAccounts()){
            if(tmp.getId() == id){
                // this is the account to remove
                accountToRemove = tmp;
            }
        }

        if(accountToRemove != null) {
            fetchedUser.getOtherAccounts().remove(accountToRemove);
        }
        userRepository.save(fetchedUser);
        // send them to the home page
        return "redirect:/mvc/secure/account/savings";
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addAccount(@RequestParam("id") Long id, HttpServletRequest request){
        // get current logged in user, need to case (TheUser) to proper type
        TheUser user = (TheUser)request.getSession().getAttribute("user");

        // refetch user from db
        TheUser fetchedUser = userRepository.findOne(user.getId());

        Account accountToAddToSavings = accountRepository.findOne(id);

        if(fetchedUser.getOtherAccounts() == null){
            fetchedUser.setOtherAccounts(new HashSet<>());
        }
        fetchedUser.getOtherAccounts().add(accountToAddToSavings);

        userRepository.save(fetchedUser);
        // send them to the home page
        return "redirect:/mvc/secure/account/savings";
    }
}
