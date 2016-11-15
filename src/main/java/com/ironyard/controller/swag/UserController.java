package com.ironyard.controller.swag;

import com.ironyard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    


}
