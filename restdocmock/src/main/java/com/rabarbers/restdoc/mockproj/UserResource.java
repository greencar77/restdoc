package com.rabarbers.restdoc.mockproj;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.cache.NoCache;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @GET
    @NoCache
    @Path("/{globalId: \\d*}")
    public Long getCatalogId(@PathParam("globalId") String globalId) {

        return 1L;
    }

}
