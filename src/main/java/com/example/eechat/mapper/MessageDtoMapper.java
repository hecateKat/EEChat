package com.example.eechat.mapper;

import com.example.eechat.dto.MessageDto;
import com.example.eechat.model.MessageModel;

import java.util.UUID;


public class MessageDtoMapper {

    public static MessageModel toModel(MessageDto messageDto){
        return MessageModel.builder()
                .id(UUID.randomUUID())
                .channelName(messageDto.getChannelName())
                .username(messageDto.getUsername())
                .text(messageDto.getText())
                .build();
    }

    public static MessageDto toDto(MessageModel messageModel){
        return MessageDto.builder()
                .channelName(messageModel.getChannelName())
                .username(messageModel.getUsername())
                .text(messageModel.getText())
                .time(messageModel.getTime())
                .build();
    }
}
