package com.example.eechat.repository.implementation;

import com.example.eechat.entity.UserEntity;
import com.example.eechat.repository.ChannelEntityRepository;
import com.example.eechat.repository.UserEntityRepository;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Setter
public class UserEntityRepositoryImpl implements UserEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ChannelEntityRepository channelEntityRepository;

    @Override
    public void saveEntity(UserEntity userEntity) {
       entityManager.persist(userEntity);
    }

    @Override
    @SneakyThrows
    public Optional<UserEntity> findByName(String name) {
        Optional<UserEntity> singleResult = Optional.of((UserEntity) entityManager
                .createQuery("SELECT u FROM UserEntity AS u WHERE u.username LIKE :name")
                .setParameter("name", name).getSingleResult());
        return singleResult;
    }

    @Override
    public void deleteEntity(String name) {
        Optional<UserEntity> userEntity = findByName(name);
        userEntity.ifPresent(e -> {
            e.getChannelEntities().forEach(v ->
                    channelEntityRepository.deleteUserFromChannel(e, v));
            entityManager.remove(userEntity);
        });
    }
}