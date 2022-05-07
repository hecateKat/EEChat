package com.example.eechat.repository;

import com.example.eechat.entity.ChannelEntity;
import com.example.eechat.entity.FileEntity;

import java.util.Optional;

public interface FileEntityRepository {

    Optional<FileEntity> findByName (String name);

    String save (byte[] content, String name);

    void send (FileEntity fileEntity, byte[] content, ChannelEntity channelEntity);

    byte[] download (String location);
}
