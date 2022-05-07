package com.example.eechat.mapper;

import com.example.eechat.dto.UserDto;
import com.example.eechat.model.UserModel;

public class UserDtoMapper {

    public static UserDto toDto(UserModel userModel){
        return UserDto.builder()
                .username(userModel.getUsername())
                .build();
    }

    public static UserModel toModel(UserDto userDto){
        return UserModel.builder()
                .username(userDto.getUsername())
                .build();
    }
}
