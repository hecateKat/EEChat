package com.example.eechat.service;

import com.example.eechat.model.MessageModel;

public interface MessageService {

    void send(MessageModel messageModel, String name);
}
