package com.ironyard.repositories;

import com.ironyard.data.RiskFreeAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by nathanielellsworth on 11/15/16.
 */
public interface RiskFreeRepository extends PagingAndSortingRepository<RiskFreeAccount, Long> {

}
