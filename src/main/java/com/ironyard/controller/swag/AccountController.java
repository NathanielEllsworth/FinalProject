package com.ironyard.controller.swag;

import com.ironyard.data.Account;
import com.ironyard.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  laying the pipes
 *
 * Created by nathanielellsworth on 11/14/16.
 */
@RestController
@RequestMapping(path = "/rest/account")
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountRepository acctRepo;


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Account save(@RequestBody Account aAccount){
        log.debug("Begin save:" + aAccount);
        acctRepo.save(aAccount);
        Account found = acctRepo.findOne(aAccount.getId());
        return found;
    }


    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Account update(@RequestBody Account aAccount){
        log.debug("begin update:" + aAccount);
        acctRepo.save(aAccount);
        Account found = acctRepo.findOne(aAccount.getId());
        log.debug("End update:" + found);
        return found;
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Account show(@PathVariable Long id){
        log.debug("Begin show:" + id);
        Account found = acctRepo.findOne(id);
        log.debug("End show:" + found);
        return found;
    }



    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<Account> listAll(@RequestParam("page") Integer page,
                                     @RequestParam("size") Integer size,
                                     @RequestParam(value = "sortby", required = false) String sortby,
                                     @RequestParam(value = "dir", required = false)Sort.Direction direction){

        log.debug(String.format("Begin listAll (page:%s, size:%s, sortby:%s, dir:%s):", page, size, sortby, direction));


        //Auto sorting default
        if(sortby == null){
            sortby = "title";
        }

        //Auto sorting direction default
        if(direction == null){
            direction = Sort.Direction.DESC;
        }
        Sort s = new Sort(direction, sortby);
        PageRequest pr = new PageRequest(page, size, s);
        Iterable<Account> found = acctRepo.findAll(pr);
        log.debug(String.format("End listAll: %s", found));

        return found;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Account delete(@PathVariable Long id){
        log.debug(String.format("Begin delete: %s", id));
        Account deleted = acctRepo.findOne(id);
        acctRepo.delete(id);
        log.debug(String.format("End delete: %s", deleted));
        return deleted;
    }



    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e){
        log.error("Error in Account Controller", e);
        return "Something went wrong (ac)";
    }


}
