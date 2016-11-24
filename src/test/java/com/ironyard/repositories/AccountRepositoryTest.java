package com.ironyard.repositories;

import com.ironyard.data.Account;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/**
 * Created by nathanielellsworth on 11/14/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountRepositoryTest {
    @Before
    public void setUp() throws Exception {
        Iterable<Account> accounts = acctRepo.findAll();
        for(Account a:accounts){
            acctRepo.delete(a);
        }

    }


    @Autowired
    private AccountRepository acctRepo;


}