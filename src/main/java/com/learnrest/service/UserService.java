package com.learnrest.service;

import com.learnrest.dao.DAO;
import com.learnrest.dao.UserDAO;
import com.learnrest.model.User;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 *
 * @author fernando
 */
@ManagedBean
@Path("/user")
public class UserService extends GenericCRUDService<User> {

    @Inject
    private UserDAO userDAO;

    public UserService() {
        super(User.class);
    }

//    @GET
//    @Path("/root")
//    @Produces(MediaType.APPLICATION_JSON)
//    public User getUser() {
//        User root = userDAO.findByUsername("root");
//        return root;
//    }
    @Override
    public DAO getDao() {
        return userDAO;
    }
}
