package com.ironyard.repositories;

import com.ironyard.data.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by nathanielellsworth on 11/14/16.
 */
public interface AccountRepository extends PagingAndSortingRepository<Account, Long>{

}
