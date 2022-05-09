package com.example.eechat.controller;

import com.example.eechat.dto.ChannelUserDto;
import com.example.eechat.dto.MessageDto;
import com.example.eechat.model.MessageModel;
import com.example.eechat.service.ChannelService;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.eechat.mapper.MessageDtoMapper.toDto;
import static javax.ws.rs.core.Response.noContent;
import static javax.ws.rs.core.Response.ok;

@Path("/channel")
@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ChannelController {

    @Inject
    private ChannelService service;

    @POST
    @Path("/new_user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserToChat(ChannelUserDto userDto){
        try{
            service.addUserToChat(userDto.getUsername(), userDto.getChannelName());
        } catch (Exception exception){
            return noContent().build();
        }
        return ok().build();
    }

    @DELETE
    @Path("/delete_user")
    public Response deleteUserFromChat(ChannelUserDto userDto){
        try{
            service.deleteUserFromChat(userDto.getUsername(), userDto.getChannelName());
        } catch (Exception exception){
            return noContent().build();
        }
        return ok().build();
    }

    @GET
    @Path("/download/{channelName}/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response downloadChatHistoryForUser(@PathParam("channelName") String channelName, @PathParam("username") String username){
        List<MessageModel> messageModelList;
        try {
            messageModelList = service.findAllMessages(channelName, username);
        } catch (Exception e) {
            return noContent().build();
        }

        List<MessageDto> messageDtos = messageModelList.stream()
                .map(v -> toDto(v))
                .collect(Collectors.toList());

        return ok().build();
    }

}
