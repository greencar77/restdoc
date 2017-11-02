package com.rabarbers.restdoc.mockproj;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.cache.NoCache;

import com.rabarbers.restdoc.mockproj.domain.Order;

@Path("/catalogs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CatalogResource {

    @POST
    @NoCache
    @Path("/list")
    public Response getCatalogs(
            @PathParam("releaseVersion") Float releaseVersion, Map<String, Collection<String>> queryParams,
            @QueryParam("delta") Float deltaVersion,
            @HeaderParam("HTTP_CUSTOMER") String customer) throws Exception {

        return Response.ok("aaa").build();
    }

    @GET
    @NoCache
    @Path("/id")
    public Long getCatalogId() {

        return 1L;
    }

    @GET
    @Path("/orders")
    public List<Order> getOrderList() {

        return null;
    }

    @PUT
    @NoCache
    @Path("/id")
    public void createCatalog(List<Order> orders) {

    }

    @GET
    @Path("/something/{id: \\d*}")
    public List<Order> getSomething() {

        return null;
    }

}
