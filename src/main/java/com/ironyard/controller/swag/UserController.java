package com.ironyard.controller.swag;

import com.ironyard.data.User;
import com.ironyard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GO TO BED MAN! IT WILL BE HERE TOMORROW!
 *
 * Created by nathanielellsworth on 11/4/16.
 */
@RestController
@RequestMapping(path = "/rest/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepo;



    @RequestMapping(value = "save", method = RequestMethod.POST)
    public User save(@RequestBody User aUser){
        log.debug("Begin save:" + aUser);
        userRepo.save(aUser);
        User found = userRepo.findOne(aUser.getId());
        log.debug("End save:" + aUser);
        return found;
    }



    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public User update(@RequestBody User aUser){
        log.debug("Begin update:" + aUser);
        userRepo.save(aUser);
        User found = userRepo.findOne(aUser.getId());
        log.debug("End update:" + found);
        return found;
    }


    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    public User show(@PathVariable Long id){
        log.debug("Begin show:" + id);
        User found = userRepo.findOne(id);
        log.debug("End show:" + found);
        return found;
    }
















}
