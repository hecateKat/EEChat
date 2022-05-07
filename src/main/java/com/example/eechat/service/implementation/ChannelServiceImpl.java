package com.example.eechat.service.implementation;

import com.example.eechat.model.ChannelModel;
import com.example.eechat.model.MessageModel;
import com.example.eechat.model.UserModel;
import com.example.eechat.repository.adaptor.ChannelAdaptor;
import com.example.eechat.repository.adaptor.MultipleChannelsAdaptor;
import com.example.eechat.repository.adaptor.UserAdaptor;
import com.example.eechat.service.ChannelService;

import java.util.List;
import java.util.stream.Collectors;

public class ChannelServiceImpl implements ChannelService {

    private final ChannelAdaptor channelAdaptor;

    private final MultipleChannelsAdaptor multipleChannelsAdaptor;

    private final UserAdaptor userAdaptor;

    public ChannelServiceImpl(ChannelAdaptor channelAdaptor, MultipleChannelsAdaptor multipleChannelsAdaptor, UserAdaptor userAdaptor) {
        this.channelAdaptor = channelAdaptor;
        this.multipleChannelsAdaptor = multipleChannelsAdaptor;
        this.userAdaptor = userAdaptor;
    }

    @Override
    public void addUserToChat(String username, String channel) {
        channelAdaptor.connectUserToChannel(getUserModel(username), getChannelModel(channel));
    }

    @Override
    public List<MessageModel> findAllMessages(String username, String channel) {
        ChannelModel channelModel = getChannelModel(channel);
        checkIfExists(channelModel, username);
        List<MessageModel> messageModels =
                channelModel
                        .getMessages()
                        .stream()
                        .filter(v -> v.getUsers().contains(username))
                        .collect(Collectors.toList());
        messageModels.forEach(x-> x.setChannelName(channel));
        return messageModels;
    }

    @Override
    public void deleteUserFromChat(String username, String channel) {
        channelAdaptor.delete(getUserModel(username), getChannelModel(channel));
    }

    private UserModel getUserModel(String username) {
        UserModel userModel = userAdaptor.findByName(username).orElseThrow(RuntimeException::new);
        return userModel;
    }

    private ChannelModel getChannelModel(String channel) {
        ChannelModel channelModel = multipleChannelsAdaptor.findByName(channel).orElseThrow(RuntimeException::new);
        return channelModel;
    }

    private UserModel checkIfExists(ChannelModel channelModel,String username){
        return channelModel.getUsers()
                .stream()
                .filter(v -> v.getUsername().equals(username))
                .findAny()
                .orElseThrow(RuntimeException::new);

    }
}