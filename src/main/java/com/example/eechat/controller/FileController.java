package com.example.eechat.controller;

import com.example.eechat.dto.FileDto;
import com.example.eechat.model.FileModel;
import com.example.eechat.service.FileService;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.example.eechat.mapper.FileDtoMapper.toDto;
import static com.example.eechat.mapper.FileDtoMapper.toModel;
import static javax.ws.rs.core.Response.ok;

@WebServlet(urlPatterns ="/files")
@RequestScoped
@Named
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class FileController {

    @Inject
    private FileService fileService;

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