package com.example.eechat.mapper;

import com.example.eechat.dto.FileDto;
import com.example.eechat.model.FileModel;

import javax.inject.Named;
import java.util.Base64;

@Mapper(componentModel = "cdi")
public class FileDtoMapper {


    @Mapping(source = "text", target = "text", qualifiedByName = "textToBytes")
    public static FileModel toModel(FileDto fileDto){
        return FileModel.builder()
                .id(fileDto.getId())
                .username(fileDto.getUsername())
                .name(fileDto.getName())
                .time(fileDto.getTime())
                .build();
    }

    @Named("textToBytes")
    static byte[] textToBytes(String content){
        return Base64.getDecoder().decode(content);
    }



    @Mapping(source = "content", target = "content", qualifiedByName = "contentToString")
    public static FileDto toDto(FileModel fileModel){
        return FileDto.builder()
                .id(fileModel.getId())
                .username(fileModel.getUsername())
                .name(fileModel.getName())
                .time(fileModel.getTime())
                .build();
    }

    @Named("contentToString")
    static String bytesToString(byte[] content){
        return Base64.getEncoder().encodeToString(content);
    }
}
