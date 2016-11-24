package com.ironyard.controller.swag;

import com.ironyard.data.TheUser;
import com.ironyard.repositories.TheUserRepository;
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
public class TheUserController {
private final Logger log = LoggerFactory.getLogger(this.getClass());

@Autowired
private TheUserRepository theUserRepository;



@RequestMapping(value = "save", method = RequestMethod.POST)
public TheUser save(@RequestBody TheUser aUser) {
        log.debug("Begin save:" + aUser);
        theUserRepository.save(aUser);
        TheUser found = theUserRepository.findOne(aUser.getId());
        log.debug("End save:" + aUser);
        return found;
        }


@RequestMapping(value = "update", method = RequestMethod.PUT)
public TheUser update(@RequestBody TheUser aUser) {
        log.debug("Begin update:" + aUser);
        theUserRepository.save(aUser);
        TheUser found = theUserRepository.findOne(aUser.getId());
        log.debug("End update:" + found);
        return found;
        }


@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
public TheUser show(@PathVariable Long id) {
        log.debug("Begin show:" + id);
        TheUser found = theUserRepository.findOne(id);
        log.debug("End show:" + found);
        return found;

        }


@RequestMapping(value = "list", method = RequestMethod.GET)
public Iterable<TheUser> listAll(@RequestParam("page") Integer page,
@RequestParam("size") Integer size,
@RequestParam(value = "sortby", required = false) String sortby,
@RequestParam(value = "dir", required = false) Sort.Direction direction) {

        log.debug(String.format("Begin listAll (page:%s, size:%s, sortby:%s, dir:%s):",page,size,sortby,direction));

        // DEFAULT Sort property
        if (sortby == null) {
        sortby = "displayName";
        }

        // DEFAULT Sort direction
        if (direction == null) {
        direction = Sort.Direction.DESC;
        }
        Sort s = new Sort(direction, sortby);
        PageRequest pr = new PageRequest(page, size, s);
        Iterable<TheUser> found =  theUserRepository.findAll(pr);
        log.debug(String.format("End listAll: %s", found));

        return found;
        }


@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
public TheUser delete(@PathVariable Long id) {
        log.debug(String.format("Begin delete: %s", id));
        TheUser deleted = theUserRepository.findOne(id);
        theUserRepository.delete(id);
        log.debug(String.format("End delete: %s", deleted));
        return deleted;
        }


@ExceptionHandler(value = Throwable.class)
public String nfeHandler(Throwable e) {
        log.error("Error in TheUserController", e);
        return "Something went wrong (uc)";
    }




}
