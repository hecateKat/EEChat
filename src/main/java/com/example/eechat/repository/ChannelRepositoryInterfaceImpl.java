package com.example.eechat.repository;

import com.example.eechat.entity.ChannelEntity;
import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Setter
@Getter
@Log
public class ChannelRepositoryInterfaceImpl implements ChannelRepositoryInterface {

    private EntityManager entityManager;

    public ChannelEntity saveChannelEntity(ChannelEntity channelEntity){
        entityManager.persist(channelEntity);
        return channelEntity;
    }

    @Override
    public Optional<ChannelEntity> findOneChannel(String channel) {
        var channelEntity = entityManager
                .createQuery("SELECT v FROM ChannelEntity v where v.name = :name", ChannelEntity.class)
                .setParameter("name", channel)
                .getSingleResult();
        return Optional.ofNullable(channelEntity);
    }

    @Override
    public Optional<List<String>> findAll() {
        List<String> publicChannels = entityManager
                .createQuery("SELECT v.name from ChannelEntity v where v.isPrivate = false", String.class)
                .getResultStream().collect(Collectors.toList());
        return Optional.of(publicChannels);
    }

    @Override
    @SneakyThrows
    public void addUserToChannel(String name, String user) {
        Optional<ChannelEntity> channelEntity = findOneChannel(name);
        if(!channelEntity.get().getUserNames().contains(user)) {
            channelEntity.get().getUserNames().add(user);
            saveChannelEntity(channelEntity.get());
            log.info("This user has been added to the channel");
        }
    }


    @Override
    @SneakyThrows
    public Optional<List<String>> getMessages(String channel, String user) {
        var channelEntity = entityManager
                .createQuery("SELECT v FROM ChannelEntity v JOIN FETCH v.channelMessages where v.name = :name ", ChannelEntity.class)
                .setParameter("name", channel)
                .getSingleResult();
        return Optional.ofNullable(channelEntity.getChannelMessages());
    }

    @Override
    @SneakyThrows
    public void addMessageToChannelLogs(String channel, String content) {
        Optional<ChannelEntity> channelEntity = findOneChannel(channel);
        channelEntity.get().getChannelMessages().add(content);
        saveChannelEntity(channelEntity.get());
        log.info("Message archivied");
    }
}
