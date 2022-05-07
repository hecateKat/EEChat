package com.example.eechat.repository;

import com.example.eechat.entity.ChannelEntity;

import java.util.List;
import java.util.Optional;

public interface MultipleChannelsRepository {

    List<ChannelEntity> findAll();

    Optional<ChannelEntity> findByName(String name);

    void create(ChannelEntity channelEntity);

    void remove(String channelName);
}
