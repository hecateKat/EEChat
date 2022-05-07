package com.example.eechat.mapper;

import com.example.eechat.dto.MessageDto;
import com.example.eechat.model.MessageModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.UUID;

@Mapper(componentModel = "cdi", imports = {Instant.class, UUID.class})
public class MessageMapper {

    @Mapping(target = "createTime", expression = "java(java.sql.Timestamp.from(Instant.now()))")
    @Mapping(target = "messageId", expression = "java(java.util.UUID.randomUUID())")
    public static MessageModel toModel(MessageDto messageDto){
        return MessageModel.builder()
                .username(messageDto.getUsername())
                .text(messageDto.getText())
                .channelName(messageDto.getChannelName())
                .time(messageDto.getTime())
                .build();
    }

    public static MessageDto ToDto(MessageModel messageModel){
        return MessageDto.builder()
                .username(messageModel.getUsername())
                .text(messageModel.getText())
                .channelName(messageModel.getChannelName())
                .time(messageModel.getTime())
                .build();
    }
}