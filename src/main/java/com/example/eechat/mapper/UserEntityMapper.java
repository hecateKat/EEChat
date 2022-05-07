package com.example.eechat.mapper;

import com.example.eechat.dto.UserDto;
import com.example.eechat.entity.UserEntity;
import com.example.eechat.model.UserModel;

public class UserEntityMapper {

    public static UserEntity toEntity(UserModel model){
        return UserEntity.builder()
                .id(model.getId())
                .username(model.getUsername())
                .date(model.getTime())
                .build();
    }

    public static UserModel toModel(UserEntity entity){
        return UserModel.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .time(entity.getDate())
                .build();
    }
}