package com.example.eechat.service;

import com.example.eechat.model.FileModel;

public interface FileService {

    FileModel findByName(String name, String username, String channel);

    void send(String name, String username, byte[] text, String channel);
}
