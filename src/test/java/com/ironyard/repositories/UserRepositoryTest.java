package com.ironyard.repositories;

import com.ironyard.data.Permission;
import com.ironyard.data.RiskFreeAccount;
import com.ironyard.data.TheUser;

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
public class UserRepositoryTest {

    @Autowired
    private PermissionRepository permRepo;

    @Autowired
    private RiskFreeRepository riskRepo;

    @Autowired
    private TheUserRepository userRepo;

    @Test
    public void testUserAccount() throws Exception{
        //create account
        RiskFreeAccount savedRiskFreeAccount = riskRepo.save(new RiskFreeAccount("12-10-2014", "transfer", "EB to US Treasury Dept", 5000.00, 5000.00, "3 Months", 0.24, 0.01, 2299.99, 150000.00));

        //create permission
        Permission savedPermission = permRepo.save(new Permission("BUY_TREASURY_BILLS", "Ability to buy Treasury Bills with an account balance of $1,000 or more."));

        //create user
        TheUser tstUser = new TheUser("Nate Ellsworth", "password", "nate");
        tstUser.setriskFree(new HashSet());
        tstUser.getriskFree().add(savedRiskFreeAccount);

        tstUser.setApproval(new HashSet());
        tstUser.getApproval().add(savedPermission);
        userRepo.save(new TheUser());

        // confirm all relationships
        TheUser fetchedUser = userRepo.findOne(tstUser.getId());

        assertEquals(savedPermission.getId(), fetchedUser.getApproval().iterator().next().getId());
        assertEquals(savedRiskFreeAccount.getId(), fetchedUser.getriskFree().iterator().next().getId());


        //long riskFreeId = fetchedUser.getriskFree().iterator().next().getId();
        //long permId = fetchedUser.getApproval().iterator().next().getId();

        //testing delete
        //userRepo.delete(fetchedUser.getId());

        // account permission should still exist
        //Assert.assertNotNull(riskRepo.findOne(riskFreeId));
        //Assert.assertNotNull(permRepo.findOne(permId));


    }

}