package com.example.eechat.mapper;

import com.example.eechat.dto.ChannelDto;
import com.example.eechat.dto.UserDto;
import com.example.eechat.model.ChannelModel;
import com.example.eechat.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ChannelDtoMapper {

    public static ChannelDto toDto(ChannelModel model) {
        List<UserDto> userDtos = new ArrayList<>();

        for (UserModel user : model.getUsers()) {
            userDtos.add(UserDtoMapper.toDto(user));
        }

        return ChannelDto.builder()
                .id(model.getId())
                .name(model.getName())
                .users(userDtos)
                .build();

    }

    public static ChannelModel toModel(ChannelDto channel) {
        List<UserModel> userModels = new ArrayList<>();

        for (UserDto user : channel.getUsers()) {
            userModels.add(UserDtoMapper.toModel(user));
        }

        return ChannelModel.builder()
                .id(channel.getId())
                .name(channel.getName())
                .users(userModels)
                .build();
    }
}