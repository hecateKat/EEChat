package com.example.eechat.controller;

import com.example.eechat.dto.FileDto;
import com.example.eechat.model.FileModel;
import com.example.eechat.service.FileService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.example.eechat.mapper.FileDtoMapper.toDto;
import static com.example.eechat.mapper.FileDtoMapper.toModel;
import static javax.ws.rs.core.Response.ok;

@Path("/files")
public class FileController {

    private FileService fileService;

    @Inject
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response send(FileDto dto){
        FileModel fileModel = toModel(dto);
        try{
            fileService.send(fileModel.getName(),
                    fileModel.getUsername(),
                    fileModel.getText(),
                    dto.getChannelName());
        } catch (Exception exception){
            return ok().entity(exception.getCause()).build();
        }
        return ok().build();
    }

    @Path("/{channelName}_{username}_{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response receiveFile(@PathParam("channelName") String channelName, @PathParam("username") String username, @PathParam("name") String name) {
        FileModel fileModel;
        try{
            fileModel = fileService.findByName(name, username, channelName);
        } catch (Exception exception){
            return ok().entity(exception.getCause()).build();
        }
        FileDto fileDto = toDto(fileModel);
        return ok().entity(fileDto).build();
    }
}