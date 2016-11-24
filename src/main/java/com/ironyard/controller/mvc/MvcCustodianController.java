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
@RequestMapping(path = "/mvc/secure/custodian")
public class MvcCustodianController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TheUserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String list(Model model) {
        String destination = "/secure/custodian_user";
        addPermListAndUser(model);
        return destination;
    }

    @RequestMapping(value = "user/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model){
        String destination = "/secure/custodian_user";
        TheUser editUser = userRepository.findOne(id);

        HashMap<String, String> userPermission = new HashMap<>();
        for(Permission p: editUser.getAbilities()){
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

        TheUser user = (TheUser)req.getSession().getAttribute("user");
        if(user.getId() == id){
            model.addAttribute("error_message", "Can not delete currently logged in user.");
        }else{
            userRepository.delete(id);
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

        String destination = "redirect:/mvc/secure/custodian/users";

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
                TheUser aNewUser = new TheUser();
                aNewUser.setUsername(username);
                aNewUser.setDisplayName(displayname);
                aNewUser.setPassword(password);

                if(perms != null){
                    aNewUser.setAbilities(new HashSet<>());
                    //fetch perms
                    for (int i = 0; i < perms.length; i++){
                        aNewUser.getAbilities().add(permissionRepository.findOne(perms[i]));
                    }
                }
                userRepository.save(aNewUser);
            }else{

                //edit the user
                TheUser editUser = userRepository.findOne(id);
                editUser.setUsername(username);
                editUser.setDisplayName(displayname);
                editUser.setPassword(password);

                //clear out any and all existing permissions
                if(editUser.getAbilities() != null){
                    editUser.getAbilities().clear();
                }else{
                    editUser.setAbilities(new HashSet<>());
                }

                // add any selected
                if(perms != null && perms.length >0) {
                    //fetch permissions and add
                    for (int i = 0; i < perms.length; i++) {
                        editUser.getAbilities().add(permissionRepository.findOne(perms[i]));
                    }
                }
                userRepository.save(editUser);
            }
        }
        return destination;
    }










    private void addPermListAndUser(Model model){
        Iterable<TheUser> users = userRepository.findAll();
        model.addAttribute("users", users);

        Iterable<Permission> permissions = permissionRepository.findAll();
        model.addAttribute("permissions", permissions);
    }



}
