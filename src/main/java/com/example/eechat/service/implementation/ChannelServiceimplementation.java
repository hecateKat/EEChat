package com.example.eechat.service.implementation;

import com.example.eechat.dto.ChannelDto;
import com.example.eechat.mapper.ChannelMapper;
import com.example.eechat.repository.ChannelRepositoryInterfaceImpl;
import com.example.eechat.service.ChannelService;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
public class ChannelServiceimplementation implements ChannelService {

    private final ChannelRepositoryInterfaceImpl channelRepositoryInterface;

    @Override
    public ChannelDto createNew(ChannelDto channelDto) {
        return ChannelMapper
                .toDto(channelRepositoryInterface
                        .saveChannelEntity(ChannelMapper
                                .toEntity(channelDto)));
    }

    @Override
    public Optional<ChannelDto> findByName(String name) {
        return channelRepositoryInterface.findOneChannel(name).map(ChannelMapper::toDto);
    }

    @Override
    public Optional<List<String>> getMessages(String channel, String user) {
        return channelRepositoryInterface.getMessages(channel, user);
    }

    @Override
    public Optional<List<String>> findAll() {
        return channelRepositoryInterface.findAll();
    }

    @Override
    public void addMessageToChannelLogs(String channel, String content) {
        channelRepositoryInterface.addMessageToChannelLogs(channel, content);
    }

    @Override
    public void addUserToChannel(String name, String user) {
        channelRepositoryInterface.addUserToChannel(name, user);
    }
}
