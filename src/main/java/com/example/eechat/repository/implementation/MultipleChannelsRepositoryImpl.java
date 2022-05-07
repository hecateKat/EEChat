package com.example.eechat.repository.implementation;

import com.example.eechat.entity.ChannelEntity;
import com.example.eechat.repository.MultipleChannelsRepository;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Setter
public class MultipleChannelsRepositoryImpl implements MultipleChannelsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ChannelEntity> findAll() {
        TypedQuery<ChannelEntity> typedQuery = entityManager.createQuery("SELECT v FROM ChannelEntity AS v", ChannelEntity.class);
        List<ChannelEntity> resultList = typedQuery.getResultList();
        return resultList;
    }

    @Override
    @SneakyThrows
    public Optional<ChannelEntity> findByName(String name) {
        Optional<ChannelEntity> singleResult = Optional.of((ChannelEntity) entityManager.createQuery("SELECT v FROM ChannelEntity AS v WHERE v.name LIKE :name")
                .setParameter("name", name).getSingleResult());
        return singleResult;
    }

    @Override
    public void create(ChannelEntity channelEntity) {
        entityManager.persist(channelEntity);
    }

    @Override
    public void remove(String channelName) {
        entityManager.remove(findByName(channelName).get());
    }
}
