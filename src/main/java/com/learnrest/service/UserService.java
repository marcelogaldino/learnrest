package com.learnrest.service;

import com.learnrest.dao.UserDAO;
import com.learnrest.model.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author fernando
 */
@ManagedBean
@Path("/user")
public class UserService implements Serializable {

    @Inject
    private UserDAO userDAO;

    @GET
    @Path("/{param}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getById(@PathParam("param") Long id) {
        User user = userDAO.findById(id);
        return user;
    }

    @GET
    @Path("search/{param}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getByUsername(@PathParam("param") String username) {
        User user = userDAO.findByUsername(username);
        return user;
    }

    @GET
    @Path("login/{username}/{password}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getByCredentials(@PathParam("username") String username, @PathParam("password") String password) {
        User user = userDAO.findByCredentials(username, password);
        return user;
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> getAll() {
        List<User> users = userDAO.findAll();
        return users;
    }

    @POST
    @Path("/save")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void save(User user) {
        System.out.println(user);
        userDAO.save(user);
    }

    @PUT @Path("update/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void update(User user, @PathParam("id") Long id) {
        System.out.println("PUT: " + id);
        User finded = userDAO.findById(id);
        if(finded == null){
            return;
        }
        finded.setUsername(user.getUsername());
        finded.setPassword(user.getPassword());
        finded.setUserType(user.getUserType());
        userDAO.update(finded);
    }
    
    @PUT @Path("merge/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User merge(User user, @PathParam("id") Long id) {
        System.out.println("PUT: " + id);
        User finded = userDAO.findById(id);
        if(finded == null){
            return null;
        }
        finded.setUsername(user.getUsername());
        finded.setPassword(user.getPassword());
        finded.setUserType(user.getUserType());
        return userDAO.merge(finded);
    }
    
    @DELETE @Path("delete/{id}")
    public void delete(@PathParam("id") Long id) {
        User user = userDAO.findById(id);
        userDAO.delete(user);
    }
    //    @GET
//    @Path("/{param}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getById(@PathParam("param") Long id) {
//        Object obj = userDAO.findById(id);
//        return Response.status(200).entity(obj).build();
//    }
//    @GET
//    @Path("/all")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAll() {
//        List<User> users = userDAO.findAll();
//        return Response.status(200).entity(users).build();
//    }
}
