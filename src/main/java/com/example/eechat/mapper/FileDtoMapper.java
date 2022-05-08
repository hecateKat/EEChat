package com.example.eechat.mapper;

import com.example.eechat.dto.FileDto;
import com.example.eechat.model.FileModel;

import java.util.Base64;

public class FileDtoMapper {


    public static FileModel toModel(FileDto fileDto){
        return FileModel.builder()
                .id(fileDto.getId())
                .username(fileDto.getUsername())
                .name(fileDto.getName())
                .time(fileDto.getTime())
                .text(textToBytes(fileDto.getText()))
                .build();
    }


    public static FileDto toDto(FileModel fileModel){
        return FileDto.builder()
                .id(fileModel.getId())
                .username(fileModel.getUsername())
                .name(fileModel.getName())
                .time(fileModel.getTime())
                .text(bytesToString(fileModel.getText()))
                .build();
    }

    private static byte[] textToBytes(String content){
        return Base64.getDecoder().decode(content);
    }

    private static String bytesToString(byte[] content){
        return Base64.getEncoder().encodeToString(content);
    }
}
