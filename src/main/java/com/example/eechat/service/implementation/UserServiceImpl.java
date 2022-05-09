package com.example.eechat.service.implementation;

import com.example.eechat.model.UserModel;
import com.example.eechat.repository.adaptor.UserAdaptor;
import com.example.eechat.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.sql.Timestamp;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UserServiceImpl implements UserService {

    private final UserAdaptor userAdaptor;

    @Override
    @SneakyThrows
    public void addUserToChat(String name) {
        userAdaptor.addUserToChat(UserModel.builder()
                .id(UUID.randomUUID())
                .time(Timestamp.from(Instant.now()))
                .username(name)
                .build());
    }

    @Override
    @SneakyThrows
    public void isActive(String name) {
        userAdaptor.findByName(name);
    }

    @Override
    public void delete(String name) {
        userAdaptor.delete(name);
    }
}
