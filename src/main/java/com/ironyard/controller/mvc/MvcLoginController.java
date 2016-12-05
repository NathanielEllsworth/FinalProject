package com.ironyard.controller.mvc;

import com.ironyard.data.Permission;
import com.ironyard.data.TheUser;
import com.ironyard.repositories.TheUserRepository;
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
    private TheUserRepository userRepository;


    /**
     *
     * @param password used to authenticate existing user
     * @param username the existing user's alias
     * @param request user from user repository
     * @return the user to the home page if authenticated, if not reload the page
     */
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

    /**
     *
     * @param request for the user in the current session to logout
     * @return the user to the login page
     */
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

}
