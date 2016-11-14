package com.ironyard.repositories;

import com.ironyard.data.Permission;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by nathanielellsworth on 11/14/16.
 */
public interface PermissionRepository extends PagingAndSortingRepository <Permission, Long>{

}
