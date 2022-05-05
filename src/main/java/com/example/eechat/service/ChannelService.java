package com.example.eechat.service;

import com.example.eechat.dto.ChannelDto;

import java.util.List;
import java.util.Optional;

public interface ChannelService {

    ChannelDto createNew(ChannelDto channelDto);

    Optional<ChannelDto> findByName(String name);

    Optional<List<String>> getMessages(String channel, String user);

    Optional<List<String>> findAll();

    void addMessageToChannelLogs(String channel, String content);

    void addUserToChannel(String name, String user);

}
