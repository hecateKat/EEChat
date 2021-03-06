package com.example.eechat.controller;

import com.example.eechat.dto.UserDto;
import com.example.eechat.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.*;

@Path("users")
@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UserController {

    @Inject
    private UserService userService;


    @POST
    @Path("add_user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserDto dto){
        try {
            userService.addUserToChat(dto.getUsername());
        } catch (Exception exception) {
            return status(Status.OK).entity(exception.getMessage()).build();
        }
        return ok().build();
    }

    @GET
    @Path("{username}")
    public Response checkIfUserIsActive(@PathParam("username") String username){
        try {
            userService.isActive(username);
        } catch (Exception exception) {
            return status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
        return status(Status.OK).build();
    }

    @DELETE
    @Path("{username}")
    public Response deleteByName(@PathParam("username") String username){
        userService.delete(username);
        return noContent().build();
    }
}