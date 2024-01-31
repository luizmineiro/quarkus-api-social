package io.github.luizmineiro.quarkussocial.rest;

import io.github.luizmineiro.quarkussocial.domain.model.User;
import io.github.luizmineiro.quarkussocial.domain.repository.UserRepository;
import io.github.luizmineiro.quarkussocial.rest.dto.CreateUserRequest;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON) // Consumir json(tipo de dado que vou receber nessa api)
@Produces(MediaType.APPLICATION_JSON) // Tipo de retorno da api
public class UserResource {
    private UserRepository repository;

    @Inject
    public UserResource(UserRepository repository) {
        this.repository = repository;
    }

    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        repository.persist(user);
        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers() {
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id) {
        User user = repository.findById(id);
        if (user != null) {
            repository.delete(user);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updadeUser(@PathParam("id") Long id, CreateUserRequest userData) {
        User user = repository.findById(id);
        if (user != null) {
            user.setName(userData.getName());
            user.setAge(userData.getAge());
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
