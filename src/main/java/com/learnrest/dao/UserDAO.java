package com.learnrest.dao;

import com.learnrest.model.User;

/**
 *
 * @author fernando
 */
public interface UserDAO extends DAO<User, Long> {

    User findByUsername(String username);

    User findByCredentials(String username, String password);

}
