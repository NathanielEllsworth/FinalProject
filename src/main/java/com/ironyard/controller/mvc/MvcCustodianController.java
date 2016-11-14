package com.ironyard.controller.mvc;

import com.ironyard.data.Permission;
import com.ironyard.data.User;
import com.ironyard.repositories.AccountRepository;
import com.ironyard.repositories.PermissionRepository;
import com.ironyard.repositories.UserRepository;
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
@RequestMapping(path = "/mvc/secure/custodian")
public class MvcCustodianController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AccountRepository acctRepo; //I want the custodian to have the ability to crud the accounts

    @Autowired
    private PermissionRepository permRepo;


    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String list(Model model) {
        String destination = "/secure/custodian_user";
        addPermListAndUser(model);
        return destination;
    }

    @RequestMapping(value = "user/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model){
        String destination = "/secure/custodian_user";
        User editUser = userRepo.findOne(id);

        HashMap<String, String> userPermission = new HashMap<>();
        for(Permission p: editUser.getApproval()){
            userPermission.put(p.getKey(), p.getKey());
        }
        model.addAttribute("edit_user_permissions", userPermission);
        model.addAttribute("username", editUser.getUsername());
        model.addAttribute("displayname", editUser.getDisplayName());
        model.addAttribute("password", editUser.getPassword());
        model.addAttribute("password2", editUser.getPassword());
        model.addAttribute("id", editUser.getId());
        addPermListAndUser(model);

        return destination;

    }


    @RequestMapping(value = "user/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id, Model model, HttpServletRequest req){
        String destination = "/secure/custodian_user";

        User user = (User)req.getSession().getAttribute("user");
        if(user.getId() == id){
            model.addAttribute("error_message", "Can not delete currently logged in user.");
        }else{
            userRepo.delete(id);
        }
        addPermListAndUser(model);
        return destination;
    }

    @RequestMapping(value = "user/save", method = RequestMethod.POST)
    public String saveUser(@RequestParam("id") Long id,
                           @RequestParam("username") String username,
                           @RequestParam("displayname") String displayname,
                           @RequestParam("password") String password,
                           @RequestParam("password2") String password2,
                           @RequestParam(value = "permissions", required = false) Long[] perms,
                           Model model){

        String destination = "redirect:/mvc/secure/admin/users";

        if(!password.equals(password2)){
            model.addAttribute("error_message","Passwords do not match");
            addPermListAndUser(model);
            destination = "/secure/custodian_user";


            model.addAttribute("username", username);
            model.addAttribute("displayname", displayname);
            model.addAttribute("id", id);

        }else{
            if(id == null){
                //create the user
                User aNewUser = new User();
                aNewUser.setUsername(username);
                aNewUser.setDisplayName(displayname);
                aNewUser.setPassword(password);

                if(perms != null){
                    aNewUser.setApproval(new HashSet<>());
                    //fetch perms
                    for (int i = 0; i < perms.length; i++){
                        aNewUser.getApproval().add(permRepo.findOne(perms[i]));
                    }
                }
                userRepo.save(aNewUser);
            }else{

                //edit the user
                User editUser = userRepo.findOne(id);
                editUser.setUsername(username);
                editUser.setDisplayName(displayname);
                editUser.setPassword(password);

                //clear out any and all existing permissions
                if(editUser.getApproval() != null){
                    editUser.getApproval().clear();
                }else{
                    editUser.setApproval(new HashSet<>());
                }

                // add any selected
                if(perms != null && perms.length >0) {
                    //fetch permissions and add
                    for (int i = 0; i < perms.length; i++) {
                        editUser.getApproval().add(permRepo.findOne(perms[i]));
                    }
                }
                userRepo.save(editUser);
            }
        }
        return destination;
    }










    private void addPermListAndUser(Model model){
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);

        Iterable<Permission> permissions = permRepo.findAll();
        model.addAttribute("permissions", permissions);
    }



}
