package com.example.eechat.repository.adaptor;

import com.example.eechat.mapper.ChannelEntityMapper;
import com.example.eechat.mapper.FileEntityMapper;
import com.example.eechat.model.ChannelModel;
import com.example.eechat.model.FileModel;
import com.example.eechat.repository.FileEntityRepository;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

import static com.example.eechat.mapper.ChannelEntityMapper.*;


@AllArgsConstructor(onConstructor_ = @Inject)
@Transactional
public class FileAdaptor {

    private FileEntityRepository fileEntityRepository;

    public Optional<FileModel> findByName(String name){
        return fileEntityRepository.findByName(name).map(FileEntityMapper::toModel);
    }

    public void send(FileModel fileModel, ChannelModel channelModel){
        fileEntityRepository.send(FileEntityMapper.toEntity(fileModel), fileModel.getText(), toEntity(channelModel));
    }

    public byte[] getText(String placement){
        return fileEntityRepository.download(placement);
    }
}
