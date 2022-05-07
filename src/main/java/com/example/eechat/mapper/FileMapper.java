package com.example.eechat.mapper;

import com.example.eechat.dto.FileDto;
import com.example.eechat.entity.FileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;

@Mapper(componentModel = "cdi")
public class FileMapper {

    @Mapping(source = "content", target = "content", qualifiedByName = "contentToString")
    public static FileDto toDto(FileEntity file){
        return FileDto.builder()
                .id(file.getId())
                .username(file.getUsername())
                .filename(file.getName())
                .text(file.getPlacement())
                .time(file.getTime())
                .build();
    }

    @Named("contentToBytes")
    static byte[] stringToBytes(String text){
        return Base64.getDecoder().decode(text);
    }

    @Mapping(source = "content", target = "content", qualifiedByName = "contentToBytes")
    public static FileEntity ToModel(FileDto file){
        return FileEntity.builder()
                .id(file.getId())
                .username(file.getUsername())
                .filename(file.getFilename())
                .text(file.getText())
                .time(file.getTime())
                .build();
    }
    @Named("contentToString")
    static String bytesToString(byte[] text){
        return Base64.getEncoder().encodeToString(text);
    }
}