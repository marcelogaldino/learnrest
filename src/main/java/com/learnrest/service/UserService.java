package com.learnrest.service;

import com.learnrest.dao.UserDAO;
import com.learnrest.model.User;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("param") Long id) {
        Object obj = userDAO.findById(id);
        return Response.status(200).entity(obj).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<User> users = userDAO.findAll();
        return Response.status(200).entity(users).build();
    }
}
