package com.example.eechat.mapper;

import com.example.eechat.entity.FileEntity;
import com.example.eechat.model.FileModel;

public class FileEntityMapper {

    public static FileEntity toEntity(FileModel file){
        return FileEntity.builder()
                .id(file.getId())
                .username(file.getUsername())
                .name(file.getName())
                .placement(file.getPlacement())
                .time(file.getTime())
                .build();
    }

    public static FileModel toModel(FileEntity entity){
        return FileModel.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .name(entity.getName())
                .time(entity.getTime())
                .build();
    }
}