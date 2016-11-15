package com.ironyard.controller.swag;

import com.ironyard.data.User;
import com.ironyard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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



    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<User> listAll(@RequestParam("page") Integer page,
                                  @RequestParam("size") Integer size,
                                  @RequestParam(value = "sortby", required = false) String sortby,
                                  @RequestParam(value = "dir", required = false) Sort.Direction direction){

        log.debug(String.format("Begin listAll (page:%s, size:%s, sortby:%s, dir:%s):", page, size, sortby, direction));

        //Default Sort
        if(sortby == null){
            sortby = "displayName";
        }

        Sort s = new Sort(direction, sortby);
        PageRequest pr = new PageRequest(page, size, s);
        Iterable<User> found = userRepo.findAll(pr);
        log.debug(String.format("End listAll: %s", found));

        return found;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable Long id){
        log.debug(String.format("Begin delete: %s", id));
        User deleted = userRepo.findOne(id);
        userRepo.delete(id);
        log.debug(String.format("End delete: %s", deleted));
        return deleted;
    }



    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e){
        log.error("Error in UserController", e);
        return "Something went wrong";
    }
    



}
