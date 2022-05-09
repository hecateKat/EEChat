package com.example.eechat.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SuperBuilder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelModel {

    private UUID id;

    private String name;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private List<UserModel> users;

    private List<MessageModel> messages;
}