package org.mahefa.boot.controller;

import io.quarkus.panache.common.Sort;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.mahefa.application.fruit.FruitApplication;
import org.mahefa.dto.FruitDTO;
import org.mahefa.dto.Pageable;

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
        return fruitApplication.update(fruitDTO);
    }

    @DELETE
    @Path("/delete/{fruit_id}")
    @Tag(name = "Delete a fruit", description = "Remove a fruit")
    public boolean delete(@PathParam("fruit_id") ObjectId fruitId) {
        return fruitApplication.delete(fruitId);
    }

    @GET
    @Path("/get/{name}")
    @Tag(name = "Get a fruit", description = "Find specific fruit")
    public FruitDTO findByName(@PathParam("name") String name) {
        return fruitApplication.get(name);
    }

    @GET
    @Path("/get/all")
    @Tag(name = "Get all fruits", description = "Retrieve all the fruits")
    public List<FruitDTO> findAll() {
        return fruitApplication.findAll();
    }

    @GET
    @Path("/list")
    @Tag(name = "Pageable list", description = "Classical pageable. MongoDB will generate a find query combined with skip and limit function. Not recommended for larger data as skip and limit are time consuming")
    public Pageable<FruitDTO> findAll(@QueryParam("page") int page, @QueryParam("size") int size) {
        return fruitApplication.findAll(page, size);
    }

    @GET
    @Path("/keyset-pagination/list")
    @Tag(name = "Fast pageable list", description = "A fast pageable system for large data. This method use _id field as key as it's automatically indexed by MongoDB")
    public Pageable<FruitDTO> keySetPagination(@QueryParam("page") int page, @QueryParam("size") int size, @QueryParam("currentId") ObjectId id,
                                               @QueryParam("sortDirection") Sort.Direction direction) {
        return fruitApplication.findAll(page, size, id, direction);
    }
}
