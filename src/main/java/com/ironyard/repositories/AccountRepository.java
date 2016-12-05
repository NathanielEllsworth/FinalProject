package com.ironyard.repositories;

import com.ironyard.data.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *  Connects the User and Account where the account can be found by the user ID (look in class MvcAccountController)
 *
 * Created by nathanielellsworth on 11/14/16.
 */

public interface AccountRepository extends PagingAndSortingRepository<Account, Long>{

    Page<Account> findByUserId(long userId, Pageable pageable);
}
