package com.example.eechat.repository;

import com.example.eechat.entity.ChannelEntity;

import java.util.List;
import java.util.Optional;

public interface ChannelRepositoryInterface {

    Optional<ChannelEntity> findOneChannel (String channel);

    Optional<List<String>> findAll();

    void addUserToChannel(String name, String user);

    Optional<List<String>> getMessages(String channel, String user);

    void addMessageToChannelLogs(String channel, String content);

}
