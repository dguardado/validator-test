package com.dimas.resources;

import com.dimas.api.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloResource {

    private static final Logger logger = LoggerFactory.getLogger(HelloResource.class);

    @POST
    public void hello(@NotNull @Valid Hello.Builder hello) {
        logger.info(hello.build().toString());
    }
}
