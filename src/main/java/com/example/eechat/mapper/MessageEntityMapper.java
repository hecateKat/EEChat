package com.example.eechat.mapper;

import com.example.eechat.entity.MessageEntity;
import com.example.eechat.model.MessageModel;

public class MessageEntityMapper {

    public static MessageModel toModel(MessageEntity entity){
        return MessageModel.builder()
                .id(entity.getId())
                .username(entity.getUser())
                .text(entity.getText())
                .users(entity.getUsers())
                .time(entity.getTime())
                .users(entity.getUsers())
                .build();
    }

    public static MessageEntity toEntity(MessageModel messageModel){
        return MessageEntity.builder()
                .id(messageModel.getId())
                .user(messageModel.getUsername())
                .text(messageModel.getText())
                .time(messageModel.getTime())
                .users(messageModel.getUsers())
                .build();
    }
}