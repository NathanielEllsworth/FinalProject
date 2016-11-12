package com.ironyard.controller.mvc;

import com.ironyard.data.Permission;
import com.ironyard.data.User;
import com.ironyard.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private UserRepository userRepo;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "password", required = false) String password,
                        @RequestParam(value = "username", required = false) String username,
                        HttpServletRequest request) {
        log.info("Login attempt by:" + username);
        String destination = "open/login";
        User found = userRepo.findByUsernameAndPassword(username, password);
        if (found != null) {
            request.getSession().setAttribute("user", found);


            // add permissions to session (unlike a Joint Account, a Custodial Account allows one party to have
            // more control over the account than the other, (e.g. Parent over child)


            HashMap<String, String> permsForThisUser = new HashMap<>();
            for (Permission p : found.getApproval()) {
                permsForThisUser.put(p.getKey(), p.getKey());
            }
            request.getSession().setAttribute("user_loggedin_perms", permsForThisUser);

            // on a successful login they are sent to the personal accounts controller to fetch their personal accounts
            destination = "redirect:/mvc/secure/accounts/personal";
            log.info("found user:" + found.getId());
        }
        log.info("Login attempt result:" + destination);
        return destination;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        String destination = "/open/login";
        User found = (User) request.getSession().getAttribute("user");
        if (found != null) {
            log.info("Logging out user with id:" + found.getId());
        }
        request.getSession().invalidate();
        return destination;
    }
}
























