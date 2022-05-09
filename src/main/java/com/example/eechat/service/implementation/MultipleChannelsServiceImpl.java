package com.example.eechat.service.implementation;

import com.example.eechat.model.ChannelModel;
import com.example.eechat.repository.adaptor.MultipleChannelsAdaptor;
import com.example.eechat.service.MultipleChannelsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class MultipleChannelsServiceImpl implements MultipleChannelsService {

    private final MultipleChannelsAdaptor multipleChannelsAdaptor;


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
