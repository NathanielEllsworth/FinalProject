package com.ironyard.repositories;

import com.ironyard.data.Permission;
import com.ironyard.data.RiskFreeAccount;
import com.ironyard.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;


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
    private UserRepository userRepo;

    @Test
    public void testUserAccount() throws Exception{
        //create account
        RiskFreeAccount savedRiskFreeAccount = riskRepo.save(new RiskFreeAccount("12-10-2014", "transfer", "EB to US Treasury Dept", 5000.00, 5000.00, "3 Months", 0.24, 0.01, 2299.99, 150000.00));

        //create permission
        Permission savedPermission = permRepo.save(new Permission("BUY_TREASURY_BILLS", "Ability to buy Treasury Bills with an account balance of $1,000 or more."));

        //create user
        User tstUser = new User("Nate Ellsworth", "password", "nate");
        tstUser.setriskFreeAccount(new HashSet<>());
        tstUser.getriskFreeAccount().add(savedRiskFreeAccount);

        tstUser.setApproval(new HashSet<>());
        tstUser.getApproval().add(savedPermission);
        userRepo.save(new User());

        // confirm all relationships
        User fetchedUser = userRepo.findOne(tstUser.getId());

        assertEquals

    }

}