package com.ironyard.controller.mvc;

import com.ironyard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nathanielellsworth on 11/14/16.
 */
@Controller
@RequestMapping(path = "/mvc/secure/custodian")
public class MvcCustodianController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private Permission

}
