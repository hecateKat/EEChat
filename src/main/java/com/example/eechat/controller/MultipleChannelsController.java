package com.example.eechat.controller;

import com.example.eechat.dto.ChannelDto;
import com.example.eechat.mapper.ChannelDtoMapper;
import com.example.eechat.service.MultipleChannelsService;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.*;

@WebServlet(urlPatterns = "/channels")
@RequestScoped
@Named
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class MultipleChannelsController {

    @Inject
    private MultipleChannelsService service;

    public MultipleChannelsController(MultipleChannelsService service) {
        this.service = service;
    }

    @GET
    @Path("/all")
    public Response findAll(){
        List<ChannelDto> channelDtos = service
                .findAll()
                .stream()
                .map(ChannelDtoMapper::toDto)
                .collect(Collectors.toList());

        List<String> channels = channelDtos
                .stream()
                .map(ChannelDto::getName)
                .collect(Collectors.toList());

        return status(Status.ACCEPTED).entity(channels).build();
    }

    @POST
    @Path("/create_chat")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createChat(ChannelDto channelDto){
        try{
            service.create(channelDto.getName());
        } catch (Exception exception){
            return status(Status.CREATED).entity(exception.getCause()).build();
        }
        return ok().build();
    }

    @DELETE
    @Path("/deleted_{channelName}")
    public Response delete(@PathParam("channelName") String channelName){
        service.delete(channelName);
        return noContent().build();
    }
}
