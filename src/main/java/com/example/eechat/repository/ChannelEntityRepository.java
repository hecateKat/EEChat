package com.example.eechat.repository;

import com.example.eechat.entity.ChannelEntity;
import com.example.eechat.entity.UserEntity;

import java.util.List;

public interface ChannelEntityRepository {

    List<UserEntity> findAllUsersAttached(ChannelEntity channel);

    void connectUserToChannel(UserEntity user, ChannelEntity channel);

    void deleteUserFromChannel(UserEntity user, ChannelEntity channel);
}
