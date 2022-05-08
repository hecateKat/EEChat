package com.example.eechat.repository.adaptor;

import com.example.eechat.mapper.UserEntityMapper;
import com.example.eechat.model.ChannelModel;
import com.example.eechat.model.UserModel;
import com.example.eechat.repository.ChannelEntityRepository;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.eechat.mapper.ChannelEntityMapper.toEntity;

@AllArgsConstructor(onConstructor_ = @Inject)
@Transactional
public class ChannelAdaptor {

    private final ChannelEntityRepository channelEntityRepository;

    public void connectUserToChannel(UserModel userModel, ChannelModel channelModel){
        channelEntityRepository
                .connectUserToChannel(UserEntityMapper
                        .toEntity(userModel),
                        toEntity(channelModel));
    }

    public List<UserModel> findAllUsers(ChannelModel channelModel){
        return channelEntityRepository
                .findAllUsersAttached(toEntity(channelModel))
                .stream()
                .map(UserEntityMapper::toModel)
                .collect(Collectors.toList());
    }

    public void delete(UserModel userModel, ChannelModel channelModel){
        channelEntityRepository
                .deleteUserFromChannel(UserEntityMapper
                        .toEntity(userModel),
                        toEntity(channelModel));
    }
}