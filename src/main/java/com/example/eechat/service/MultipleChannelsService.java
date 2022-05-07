package com.example.eechat.service;

import com.example.eechat.model.ChannelModel;

import java.util.List;

public interface MultipleChannelsService {

    void create(String name);

    List<ChannelModel> findAll();

    void delete(String name);
}
