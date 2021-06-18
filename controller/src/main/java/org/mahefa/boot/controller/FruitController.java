package org.mahefa.boot.controller;

import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.mahefa.application.fruit.FruitApplication;
import org.mahefa.dto.FruitDTO;
import org.mahefa.dto.PageableDTO;

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
    @Tag(name = "Create a fruit", description = "Add fruit")
    public FruitDTO add(FruitDTO fruitDTO) {
        return this.fruitApplication.add(fruitDTO);
    }

    @PUT
    @Path("/update")
    @Tag(name = "Update a fruit", description = "Modify a fruit")
    public FruitDTO update(FruitDTO fruitDTO) {
        return this.fruitApplication.update(fruitDTO);
    }

    @DELETE
    @Path("/delete/{fruit_id}")
    @Tag(name = "Delete a fruit", description = "Remove a fruit")
    public boolean delete(@PathParam("fruit_id") ObjectId fruitId) {
        return this.fruitApplication.delete(fruitId);
    }

    @GET
    @Path("/get/{name}")
    @Tag(name = "Get a fruit", description = "Find specific fruit")
    public FruitDTO findByName(@PathParam("name") String name) {
        return this.fruitApplication.get(name);
    }

    @GET
    @Path("/get/all")
    @Tag(name = "Get all fruits", description = "Retrieve all the fruits")
    public List<FruitDTO> findAll() {
        return this.fruitApplication.findAll();
    }

    @GET
    @Path("/list")
    @Tag(name = "Pageable list", description = "Pageable list")
    public PageableDTO<FruitDTO> findAll(@QueryParam("page") int page, @QueryParam("size") int size) {
        return this.fruitApplication.findAll(page, size);
    }
}
