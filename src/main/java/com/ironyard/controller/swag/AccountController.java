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
 *  SWAGGER_2 UI for the Account Controller
 *
 * Created by nathanielellsworth on 11/14/16.
 */

@RestController
@RequestMapping(path = "/rest/account")
public class AccountController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    //forgo the set method
    @Autowired
    private AccountRepository acctRepo;


    /**
     *
     * @param aAccount lets the user save account activity (also known as the Transactions) to the account
     *                 repository
     * @return the ID of the account from the account repository
     */
    @RequestMapping(value = "save", method = RequestMethod.POST) //post for the save
    public Account save(@RequestBody Account aAccount){
        log.debug("Begin save:" + aAccount);
        acctRepo.save(aAccount);
        Account found = acctRepo.findOne(aAccount.getId());
        return found;
    }

    /**
     *
     * @param aAccount lets the user update the account activity (also known as Transactions) to the account
     * @return the ID of the account from the account repository as found
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT) //put for the update
    public Account update(@RequestBody Account aAccount){
        log.debug("begin update:" + aAccount);
        acctRepo.save(aAccount);
        Account found = acctRepo.findOne(aAccount.getId());
        log.debug("End update:" + found);
        return found;
    }

    /**
     * Get the Account by id and return it
     * @param id ..well this isn't a parameter this is a path, I'm pulling the id off of the Path Variable.
     *           setting the "get/{id}" equal to the "Long id"
     * @return
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET) //get for the id
    public Account show(@PathVariable Long id){
        log.debug("Begin show:" + id);
        Account found = acctRepo.findOne(id);
        log.debug("End show:" + found); // what was found
        return found;
    }

    /**
     * List the transactions matching the request
     * @param page of transactions
     * @param size of page (how many transactions are on a page)
     * @param sortby whatever you want, (Date, id, title, etc.)
     * @param direction ascending or descending order
     * @return
     */
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

    /**
     * Delete the specified transaction
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Account delete(@PathVariable Long id){
        log.debug(String.format("Begin delete: %s", id));
        Account deleted = acctRepo.findOne(id);
        acctRepo.delete(id);
        log.debug(String.format("End delete: %s", deleted));
        return deleted;
    }

    /**
     * Catch any errors from this controller
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e){
        log.error("Error in Account Controller", e);
        return "Something went wrong (ac)";
    }


}
