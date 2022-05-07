package com.example.eechat.service.implementation;

import com.example.eechat.model.ChannelModel;
import com.example.eechat.repository.adaptor.MultipleChannelsAdaptor;
import com.example.eechat.service.MultipleChannelsService;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class MultipleChannelsServiceImpl implements MultipleChannelsService {

    private final MultipleChannelsAdaptor multipleChannelsAdaptor;

    public MultipleChannelsServiceImpl(MultipleChannelsAdaptor multipleChannelsAdaptor) {
        this.multipleChannelsAdaptor = multipleChannelsAdaptor;
    }

    @Override
    @SneakyThrows
    public void create(String name) {
        if (!checkIfExist(name)){
            multipleChannelsAdaptor
                    .create(channelFactory(name));
        }
    }

    @Override
    public List<ChannelModel> findAll() {
        return multipleChannelsAdaptor.findAll();
    }

    @Override
    public void delete(String name) {
        multipleChannelsAdaptor.remove(name);
    }

    private ChannelModel channelFactory(String name) {
        return ChannelModel.builder()
                .id(UUID.randomUUID())
                .name(name)
                .users(new ArrayList<>())
                .build();
    }

    private boolean checkIfExist(String name){
        return multipleChannelsAdaptor
                .findAll()
                .stream()
                .map(ChannelModel::getName)
                .allMatch(v -> v.equals(name));
    }
}
