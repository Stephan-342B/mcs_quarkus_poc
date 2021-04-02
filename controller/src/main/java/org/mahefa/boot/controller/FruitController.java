package org.mahefa.boot.controller;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.mahefa.application.fruit.FruitApplication;
import org.mahefa.data_transfert_object.FruitDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitController {

    @Inject
    FruitApplication fruitApplication;

    @POST
    @Path("/create")
    @Tag(name = "Create a fruit", description = "Add a fruit")
    public List<FruitDTO> add(FruitDTO fruitDTO) {
        return this.fruitApplication.add(fruitDTO);
    }

    @GET
    @Path("/get/all")
    @Tag(name = "Get all fruits", description = "Retrieve all the fruits")
    public List<FruitDTO> findAll() {
        return this.fruitApplication.findAll();
    }

    @GET
    @Path("/get/{name}")
    @Tag(name = "Get a fruit", description = "Find specific fruit")
    public FruitDTO findByName(@PathParam("name") String name) {
        return this.fruitApplication.get(name);
    }
}
