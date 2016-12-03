package com.ironyard.repositories;

import com.ironyard.data.Account;
import com.ironyard.data.Permission;
import com.ironyard.data.TheUser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;


/**
 * Created by nathanielellsworth on 11/12/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TheUserRepositoryTest {

    @Autowired
    private PermissionRepository permRepo;

    @Autowired
    private AccountRepository acctRepo;

    @Autowired
    private TheUserRepository userRepo;


    @Test
    public void testUserDeleteShouldNotDeleteMovieOrPerm() throws Exception{

        Account savedAccount = acctRepo.save(new Account("Savings", java.sql.Date.valueOf("2016-07-04"), "Transfer", "EB to US Treasury Dept", "1000", "0", "1 Month", "0.28", "0.00083", "33513.44", "110000", "0"));

        // create permission
        Permission savedPermission = permRepo.save(new Permission("USER_BUY_TBILLS", "Ability to purchase Treasury bills."));

        // create user
        TheUser tstUser = new TheUser("nate", "password", "Nate Ellsworth");


        tstUser.setAbilities(new HashSet());
        tstUser.getAbilities().add(savedPermission);

        userRepo.save(tstUser);

        // confirm all relationships
        TheUser fetchedUser = userRepo.findOne(tstUser.getId());

        assertEquals(savedPermission.getId(), fetchedUser.getAbilities().iterator().next().getId());


        // we know saving works :D, save off related entity ids

        long permId = fetchedUser.getAbilities().iterator().next().getId();

        // test delete
        userRepo.delete(fetchedUser.getId());

        // movie should

        Assert.assertNotNull(permRepo.findOne(permId));
    }

    @Test
    public void testFindByUsernameAndPass() throws Exception{

        // create user
        TheUser tstUser = new TheUser("Nate Ellsworth", "password", "nate");
        userRepo.save(tstUser);

        // find by user pass
        TheUser found = userRepo.findByUsernameAndPassword("nate", "password");
        Assert.assertNotNull(found);
    }


}