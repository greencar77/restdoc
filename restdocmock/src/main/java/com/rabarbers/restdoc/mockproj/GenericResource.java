package com.rabarbers.restdoc.mockproj;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rabarbers.restdoc.mockproj.domain.Order;
import com.rabarbers.restdoc.mockproj.domain.Pair;

@Path("/generics")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GenericResource {

    @GET
    @Path("/list")
    public Pair<String, Integer> getPairList() {

        return null;
    }

    @GET
    @Path("/tree")
    public Pair<String, Pair<Long, Order>> getPairTree() {

        return null;
    }

}
