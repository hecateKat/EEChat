package com.example.eechat.mapper;


import com.example.eechat.entity.ChannelEntity;
import com.example.eechat.entity.MessageEntity;
import com.example.eechat.entity.UserEntity;
import com.example.eechat.model.ChannelModel;
import com.example.eechat.model.MessageModel;
import com.example.eechat.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ChannelEntityMapper {

    public static ChannelEntity toEntity(ChannelModel model) {
        List<UserEntity> userEntities = new ArrayList<>();

        for (UserModel user : model.getUsers()) {
            userEntities.add(UserEntityMapper.toEntity(user));
        }

        List<MessageEntity> messageEntities = new ArrayList<>();

        for (MessageModel messageModel : model.getMessages()) {
            messageEntities.add(MessageEntityMapper.toEntity(messageModel));
        }

        return ChannelEntity.builder()
                .id(model.getId())
                .name(model.getName())
                .users(userEntities)
                .messages(messageEntities)
                .build();

    }

    public static ChannelModel toModel(ChannelEntity channel) {
        List<UserModel> userModels = new ArrayList<>();

        for (UserEntity user : channel.getUsers()) {
            userModels.add(UserEntityMapper.toModel(user));
        }

        List<MessageModel> messageModels = new ArrayList<>();

        for (MessageEntity messageEntity : channel.getMessages()) {
            messageModels.add(MessageEntityMapper.toModel(messageEntity));
        }

        return ChannelModel.builder()
                .id(channel.getId())
                .name(channel.getName())
                .users(userModels)
                .messages(messageModels)
                .build();

    }
}
