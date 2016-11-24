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
@RequestMapping(path = "/mvc/secure/user")
public class MvcTheUserController {

    @Autowired
    TheUserRepository userRepository = null;

    @Autowired
    AccountRepository accountRepository = null;

    @RequestMapping(value = "savings/delete", method = RequestMethod.GET)
    public String deleteFavorite(@RequestParam("id") Long id, HttpServletRequest request){

        TheUser user = (TheUser)request.getSession().getAttribute("user");


        TheUser fetchedUser = userRepository.findOne(user.getId());
        Account accountToRemove = null;
        for(Account tmp: fetchedUser.getOtherAccounts()){
            if(tmp.getId() == id){
                accountToRemove = tmp;
            }
        }

        if(accountToRemove != null) {
            fetchedUser.getOtherAccounts().remove(accountToRemove);
        }
        userRepository.save(fetchedUser);

        return "redirect:/mvc/secure/account/savings";
    }

    @RequestMapping(value = "savings/add", method = RequestMethod.GET)
    public String addSavings(@RequestParam("id") Long id, HttpServletRequest request){
        TheUser user = (TheUser)request.getSession().getAttribute("user");

        TheUser fetchedUser = userRepository.findOne(user.getId());

        Account accountToAddToSavings = accountRepository.findOne(id);

        if(fetchedUser.getOtherAccounts() == null){
            fetchedUser.setOtherAccounts(new HashSet<>());
        }
        fetchedUser.getOtherAccounts().add(accountToAddToSavings);

        userRepository.save(fetchedUser);

        return "redirect:/mvc/secure/account/all";
    }




}














