package com.ironyard.controller.swag;

import com.ironyard.data.Permission;
import com.ironyard.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SWAGGER_2 UI for the Permissions Controller
 *
 * Created by nathanielellsworth on 11/14/16.
 */

@RestController
@RequestMapping(path = "/rest/permission")
public class PermissionController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());



    @Autowired
    private PermissionRepository permRepo;


    /**
     * Save the Permission
     * @param aPermission
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Permission save(@RequestBody Permission aPermission){
        permRepo.save(aPermission);
        return permRepo.findOne(aPermission.getId());
    }

    /**
     * Update the Permission
     * @param aPermission
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Permission update(@RequestBody Permission aPermission){
        permRepo.save(aPermission);
        return permRepo.findOne(aPermission.getId());
    }

    /**
     * Get the permission by id and return it
     * @param id this isn't a parameter this is a path, I'm pulling the id off of the Path Variable.
     *           setting the "get/{id}" equal to the "Long id"
     * @return
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Permission show(@PathVariable Long id){ return permRepo.findOne(id);}


    /**
     * List the permissions matching the request
     * @param page of permissions
     * @param size of page (how many permissions are on a page)
     * @param sortby the key of the permission
     * @param direction ascending or descending order
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<Permission> listAll(@RequestParam(value = "page") Integer page,
                                        @RequestParam("size") Integer size,
                                        @RequestParam(value = "sortby", required = false) String sortby,
                                        @RequestParam(value = "dir", required = false) Sort.Direction direction){

        //Sort default
        if (sortby == null){
            sortby = "key";
        }

        //Sort default direction
        if (direction == null){
            direction = Sort.Direction.DESC;
        }
        Sort s = new Sort(direction, sortby);
        PageRequest pr = new PageRequest(page, size, s);
        return permRepo.findAll(pr);
    }


    /**
     * Delete the specified permission
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Permission delete(@PathVariable Long id){
        Permission deleted = permRepo.findOne(id);
        permRepo.delete(id);
        return deleted;
    }


    /**
     * Catch any errors from this controller
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e){
        log.error("Error in Permission Controller", e);
        return("Something went wrong (pc)");
    }


}
