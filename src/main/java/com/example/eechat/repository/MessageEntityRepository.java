package com.example.eechat.repository;

import com.example.eechat.entity.ChannelEntity;
import com.example.eechat.entity.MessageEntity;

public interface MessageEntityRepository {

    void send(MessageEntity messageEntity, ChannelEntity channelEntity);
}
