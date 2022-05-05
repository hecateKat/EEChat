package com.example.eechat.mapper;


import com.example.eechat.dto.ChannelDto;
import com.example.eechat.entity.ChannelEntity;

public class ChannelMapper {

    public static ChannelEntity toEntity(ChannelDto channelDto){
        return ChannelEntity.builder()
                .name(channelDto.getName())
                .isPrivate(channelDto.isPrivate())
                .channelMessages(channelDto.getChannelMessages())
                .userNames(channelDto.getUserNames())
                .build();

    }

    public static ChannelDto toDto(ChannelEntity channelEntity){
        return ChannelDto.builder()
                .name(channelEntity.getName())
                .isPrivate(channelEntity.isPrivate())
                .channelMessages(channelEntity.getChannelMessages())
                .userNames(channelEntity.getUserNames())
                .build();
    }
}
