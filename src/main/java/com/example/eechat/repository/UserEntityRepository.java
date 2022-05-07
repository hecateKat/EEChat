package com.example.eechat.repository;

import com.example.eechat.entity.UserEntity;

import java.util.Optional;

public interface UserEntityRepository {

    void saveEntity(UserEntity userEntity);

    Optional<UserEntity> findByName(String name);

    void deleteEntity(String name);
}
