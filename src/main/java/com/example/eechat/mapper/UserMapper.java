package com.example.eechat.mapper;

import com.example.eechat.dto.UserDto;
import com.example.eechat.entity.UserEntity;
import com.example.eechat.model.UserModel;

public class UserMapper {

    public static UserDto toDto(UserModel model){
        return UserDto.builder()
                .username(model.getUsername())
                .build();
    }

    public static UserModel toModel(UserDto dto){
        return UserModel.builder()
                .username(dto.getUsername())
                .build();
    }
}