package com.example.eechat.repository.implementation;

import com.example.eechat.entity.ChannelEntity;
import com.example.eechat.entity.MessageEntity;
import com.example.eechat.repository.MessageEntityRepository;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Setter
public class MessageEntityRepositoryImpl implements MessageEntityRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void send(MessageEntity messageEntity, ChannelEntity channelEntity) {
        entityManager.persist(messageEntity);
        channelEntity.getMessages().add(messageEntity);
        entityManager.merge(channelEntity);
    }
}
