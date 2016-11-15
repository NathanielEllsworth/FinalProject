package com.ironyard.controller.swag;

import com.ironyard.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Get yo Swagger on bruh!
 *
 * Created by nathanielellsworth on 11/14/16.
 */

@RestController
@RequestMapping(path = "/rest/permission")
public class PermissionController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PermissionRepository permRepo;



    
}
