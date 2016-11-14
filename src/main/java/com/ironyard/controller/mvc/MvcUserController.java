package com.ironyard.controller.mvc;

import com.ironyard.data.Accounts;
import com.ironyard.data.User;
import com.ironyard.repositories.AccountRepository;
import com.ironyard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

/**
 * This is where the User can add, delete, create and rename their different Accounts
 *
 * Created by nathanielellsworth on 11/4/16.
 */
@Controller
@RequestMapping(path = "/mvc/secure/user")
public class MvcUserController {

    @Autowired
    UserRepository userRepo = null;

    @Autowired
    AccountRepository acctRepo = null;

    @RequestMapping(value = "personalAccounts/delete", method = RequestMethod.GET)
    public String deleteAccount(@RequestParam("id") Long id, HttpServletRequest request){

        User user = (User)request.getSession().getAttribute("user");


        User fetchedUser = userRepo.findOne(user.getId());

        Accounts removeAccount = null;
        for(Accounts temp: fetchedUser.getPersonalAccounts()){
            if(temp.getId() == id){

                // this is the account to remove
                removeAccount = temp;
            }
        }

        if(removeAccount != null){
            fetchedUser.getPersonalAccounts().remove(removeAccount);
        }
        userRepo.save(fetchedUser);
        // send to personal accounts page
        return "redirect:/mvc/secure/accounts/personalAccounts"; //************************************ remember this path
    }

    @RequestMapping(value = "personalAccounts/add", method = RequestMethod.GET)
    public String addAccount(@RequestParam("id") Long id, HttpServletRequest request){

        User user = (User)request.getSession().getAttribute("user");

        User fetchedUser = userRepo.findOne(user.getId());

        Accounts addAccount = acctRepo.findOne(id);

        if(fetchedUser.getPersonalAccounts() == null){
            fetchedUser.setPersonalAccounts(new HashSet<>());
        }
        fetchedUser.getPersonalAccounts().add(addAccount);

        userRepo.save(fetchedUser);
        // send to personal accounts page
        return "redirect:/mvc/secure/accounts/all";
    }

}














