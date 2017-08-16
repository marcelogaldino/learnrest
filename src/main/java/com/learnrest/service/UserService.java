package com.learnrest.service;

import com.learnrest.dao.UserDAO;
import com.learnrest.model.User;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author fernando
 */
@Path("/user")
public class UserService {

    @Inject
    private UserDAO userDAO;

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("param") String id) {
        User user = userDAO.findById(new Long(id));

        return Response.status(200).entity(user.toString()).build();
    }

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
