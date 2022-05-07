package com.example.eechat.repository.implementation;

import com.example.eechat.entity.ChannelEntity;
import com.example.eechat.entity.UserEntity;
import com.example.eechat.repository.ChannelEntityRepository;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Setter
public class ChannelEntityRepositoryImpl implements ChannelEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> findAllUsersAttached(ChannelEntity channel) {
        return channel.getUsers();
    }

    @Override
    public void connectUserToChannel(UserEntity user, ChannelEntity channel) {
        channel.getUsers().add(user);
        merge(channel);
    }

    @Override
    public void deleteUserFromChannel(UserEntity user, ChannelEntity channel) {
        channel.getUsers().remove(user);
        merge(channel);
    }

    private void merge(ChannelEntity channel) {
        entityManager.merge(channel);
    }
}
