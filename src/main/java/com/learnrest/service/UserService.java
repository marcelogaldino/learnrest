package com.learnrest.service;

import com.learnrest.dao.UserDAO;
import com.learnrest.model.User;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author fernando
 */
@ManagedBean
@Path("/user")
public class UserService {

    @Inject
    private UserDAO userDAO;

    @GET
    @Path("/")
    public Response getUsers() {
        List<User> users = userDAO.findAll();
        StringBuilder sb = new StringBuilder();
        for (User u : users) {
            sb.append(u.toString());
            sb.append("\n");
        }

        return Response.status(200).entity(sb.toString()).build();
    }
}
