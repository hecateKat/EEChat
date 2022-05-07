package com.example.eechat.mapper;


import com.example.eechat.dto.ChannelDto;
import com.example.eechat.dto.UserDto;
import com.example.eechat.entity.ChannelEntity;
import com.example.eechat.entity.UserEntity;
import com.example.eechat.model.ChannelModel;
import com.example.eechat.model.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChannelMapper {

    public static ChannelModel toEntity(ChannelDto channelDto){
        List<UserDto> userDtos = new ArrayList<>();

        for (UserDto userDto: channelDto.getUsers()) {
            userDtos.add(userDto);
        }

        return ChannelModel.builder()
                .id(channelDto.getId())
                .name(channelDto.getName())
                .users(userDtos.stream().map(UserMapper::toModel).collect(Collectors.toList()))
                .build();

    }

    public static ChannelDto toDto(ChannelModel channelModel){
        List<UserModel> userModels = new ArrayList<>();

        for (UserModel userModel: channelModel.getUsers()) {
            userModels.add(userModel);
        }

        return ChannelDto.builder()
                .id(channelModel.getId())
                .name(channelModel.getName())
                .users(userModels.stream().map(UserMapper::toDto).collect(Collectors.toList()))
                .build();

    }
}
