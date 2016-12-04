package com.ironyard.controller.mvc;

import com.ironyard.data.Permission;
import com.ironyard.data.TheUser;
import com.ironyard.repositories.PermissionRepository;
import com.ironyard.repositories.TheUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;

/**
 * the Custodian Controller allows a parent/guardian or spouse to have discretionary control over
 * account(s) or sub-account(s) of a loved one, this feature would be used in the same context as an
 * investment manager.
 *
 * Created by nathanielellsworth on 11/14/16.
 */

@Controller
@RequestMapping(path = "/mvc/secure/admin")
public class MvcAdminController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());



    @Autowired
    private TheUserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;


    /**
     *
     * @param model grab the users and put them in a list
     * @return the list back to the admin_user.jsp
     */
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String list(Model model) {
        String destination = "/secure/admin_user";
        addUserAndPermList(model);
        return destination;
    }

    /**
     *
     * @param id of the user to be edited
     * @param model the added/edited attributes of the user
     * @return the logged in user back to the admin_user.jsp page
     */
    @RequestMapping(value = "user/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id,
                       Model model) {
        String destination = "/secure/admin_user";
        TheUser editMe = userRepository.findOne(id);
        // add a hash of perms this user case (so we can mark them in the checkboxes)
        HashMap<String, String> permsForThisUser = new HashMap<>();
        for(Permission p: editMe.getAbilities()){
            permsForThisUser.put(p.getKey(), p.getKey());
        }

        model.addAttribute("edit_user_perms", permsForThisUser);
        model.addAttribute("username", editMe.getUsername());
        model.addAttribute("displayname", editMe.getDisplayName());
        model.addAttribute("password", editMe.getPassword());
        model.addAttribute("password2", editMe.getPassword());
        model.addAttribute("id", editMe.getId());
        addUserAndPermList(model);
        return destination;
    }

    /**
     *
     * @param id of the user being deleted
     * @param model data objects of the user
     * @param req request the Http Servlet to get the session and attribute
     * @return the user in the session back to admin_user.jsp if no errors were thrown
     */
    @RequestMapping(value = "user/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id, Model model, HttpServletRequest req) {
        String destination = "/secure/admin_user";

        // make sure user not trying to delete currently logged in user
        TheUser usr = (TheUser)req.getSession().getAttribute("user");
        if(usr.getId() == id){
            model.addAttribute("error_message", "Can not delete currently logged in user!");
        }else{
            // just delete
            userRepository.delete(id);
        }
        addUserAndPermList(model);
        return destination;
    }

    /**
     *
     * @param id auto generated id of the user to be saved
     * @param username of the new user
     * @param displayname the new user wants to use on their logged-in session
     * @param password the user wants to use when they go to login
     * @param password2 is a safety feature to make sure the user's new password was entered in correctly
     * @param perms are the abilities the user will have when the new user is saved
     * @param model the data objects of the user
     * @return the saved user and the current user in session back to the web page admin_user.jsp
     */
    @RequestMapping(value = "user/save", method = RequestMethod.POST)
    public String saveUser(@RequestParam("id") Long id,
                           @RequestParam("username") String username,
                           @RequestParam("displayname") String displayname,
                           @RequestParam("password") String password,
                           @RequestParam("password2") String password2,
                           @RequestParam(value = "permissions", required = false ) Long[] perms,
                           Model model) {

        String destination = "redirect:/mvc/secure/admin/users";
        // check password match?
        if (!password.equals(password2)) {
            // handle error?
            model.addAttribute("error_message", "Passwords do not match!");
            addUserAndPermList(model);
            destination = "/secure/admin_user";
            // keep a couple fields to be nice to user
            model.addAttribute("username", username);
            model.addAttribute("displayname", displayname);
            model.addAttribute("id", id);

        } else {
            if(id == null) {
                // create user
                TheUser myNewUser = new TheUser();
                myNewUser.setUsername(username);
                myNewUser.setDisplayName(displayname);
                myNewUser.setPassword(password);

                if (perms != null) {
                    myNewUser.setAbilities(new HashSet<>());
                    // fetch perms
                    for (int i = 0; i < perms.length; i++) {
                        myNewUser.getAbilities().add(permissionRepository.findOne(perms[i]));
                    }
                }
                userRepository.save(myNewUser);
            }else {

                // edit user
                TheUser editMe = userRepository.findOne(id);
                editMe.setUsername(username);
                editMe.setDisplayName(displayname);
                editMe.setPassword(password);
                // clear out any existing perms
                if(editMe.getAbilities() != null){
                    editMe.getAbilities().clear();
                }else{
                    editMe.setAbilities(new HashSet<>());
                }

                // add any selcted
                if (perms != null && perms.length > 0) {
                    // fetch perms and add
                    for (int i = 0; i < perms.length; i++) {
                        editMe.getAbilities().add(permissionRepository.findOne(perms[i]));
                    }
                }
                userRepository.save(editMe);
            }
        }
        return destination;
    }

    /**
     *
     * @param model the user to the permissions granted by the Admin user
     */
    private void addUserAndPermList(Model model){
        Iterable<TheUser> users = userRepository.findAll();
        model.addAttribute("users", users);

        Iterable<Permission> permissions = permissionRepository.findAll();
        model.addAttribute("permissions", permissions);
    }
}
