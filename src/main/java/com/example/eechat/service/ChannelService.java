package com.example.eechat.service;

import com.example.eechat.model.MessageModel;

import java.util.List;

public interface ChannelService {

    void addUserToChat(String username, String channel);

    List<MessageModel> findAllMessages(String username, String channel);

    void deleteUserFromChat(String username, String channel);
}
