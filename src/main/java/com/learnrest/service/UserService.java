package com.learnrest.service;

import com.learnrest.model.User;
import javax.ws.rs.core.Response;

/**
 *
 * @author fernando
 */
public interface UserService extends CRUDRestService<User> {
    
    Response getByUsername(String username);
    
    Response getByCredentials(String username, String password);
    
}
