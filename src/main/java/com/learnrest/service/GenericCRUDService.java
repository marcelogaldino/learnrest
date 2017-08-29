package com.learnrest.service;

import java.util.List;
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
public abstract class GenericCRUDService<T> implements CRUDService<T> {

    private final Class<T> clazz;

    public GenericCRUDService(Class<T> clazz) {
        this.clazz = clazz;
    }

    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getById(@PathParam("param") Long id) {
        Object obj = getDao().findById(id);
        return Response.status(200).entity(obj).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getAll() {
        List<T> objs = getDao().findAll();

        return Response.status(200).entity(objs).build();
    }
}
