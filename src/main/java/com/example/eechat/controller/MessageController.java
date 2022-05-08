package com.example.eechat.controller;

import com.example.eechat.dto.MessageDto;
import com.example.eechat.mapper.MessageDtoMapper;
import com.example.eechat.model.MessageModel;
import com.example.eechat.service.MessageService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.example.eechat.mapper.MessageDtoMapper.*;
import static javax.ws.rs.core.Response.*;

@Path("/messages")
public class MessageController {

    private MessageService service;

    @Inject
    public MessageController(MessageService service) {
        this.service = service;
    }

    @Path("/send")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response send(MessageDto messageDto){
        MessageModel message = toModel(messageDto);
        try {
            service.send(message,messageDto.getChannelName());
        } catch (Exception exception) {
            return status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
        return status(Status.ACCEPTED).build();
    }
}
