package io.github.luizmineiro.quarkussocial.rest;

import io.github.luizmineiro.quarkussocial.rest.dto.CreateUserRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON) //Consumir json(tipo de dado que vou receber nessa api)
@Produces(MediaType.APPLICATION_JSON) //Tipo de retorno da api
public class UserResource {

    @POST
    public Response createUser(CreateUserRequest userRequest){
        return Response.ok().build();
    }

    @GET
    public Response listAllUsers(){
        return Response.ok().build();
    }
}
