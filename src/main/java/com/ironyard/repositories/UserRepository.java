package com.ironyard.repositories;

import com.ironyard.data.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Find the User by their username and Password, Needed for login (look in class MvcLoginController)
 *
 * Created by nathanielellsworth on 11/12/16.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

    User  findByUsernameAndPassword(String username, String password);

}
