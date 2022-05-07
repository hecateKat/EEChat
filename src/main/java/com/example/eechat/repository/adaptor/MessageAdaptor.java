package com.example.eechat.repository.adaptor;

import com.example.eechat.entity.ChannelEntity;
import com.example.eechat.entity.MessageEntity;
import com.example.eechat.entity.UserEntity;
import com.example.eechat.mapper.ChannelEntityMapper;
import com.example.eechat.mapper.MessageEntityMapper;
import com.example.eechat.model.ChannelModel;
import com.example.eechat.model.MessageModel;
import com.example.eechat.repository.MessageEntityRepository;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(onConstructor_ = @Inject)
@Transactional
public class MessageAdaptor {

    private final MessageEntityRepository messageEntityRepository;

    public void send(MessageModel messageModel, ChannelModel channelModel){
        MessageEntity messageEntity = MessageEntityMapper.toEntity(messageModel);
        ChannelEntity channelEntity = ChannelEntityMapper.toEntity(channelModel);

        List<String> users = channelEntity
                .getUsers()
                .stream()
                .map(UserEntity::getUsername)
                .collect(Collectors.toList());

        messageEntity.getUsers().addAll(users);

        messageEntityRepository.send(messageEntity, channelEntity);
    }
}
