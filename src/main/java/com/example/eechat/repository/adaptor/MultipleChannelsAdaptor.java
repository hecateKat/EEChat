package com.example.eechat.repository.adaptor;

import com.example.eechat.mapper.ChannelEntityMapper;
import com.example.eechat.model.ChannelModel;
import com.example.eechat.repository.MultipleChannelsRepository;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.eechat.mapper.ChannelEntityMapper.*;

@AllArgsConstructor(onConstructor_ = @Inject)
@Transactional
public class MultipleChannelsAdaptor {

    private final MultipleChannelsRepository multipleChannelsRepository;

    public void create(ChannelModel channelModel){
        multipleChannelsRepository
                .create(toEntity(channelModel));
    }

    public List<ChannelModel> findAll(){
        return multipleChannelsRepository
                .findAll()
                .stream()
                .map(ChannelEntityMapper::toModel)
                .collect(Collectors.toList());
    }

    public Optional<ChannelModel> findByName(String name){
        return multipleChannelsRepository
                .findByName(name)
                .map(ChannelEntityMapper::toModel);
    }

    public void remove (String name){
        multipleChannelsRepository.remove(name);
    }
}
