package com.example.eechat.repository.implementation;

import com.example.eechat.entity.ChannelEntity;
import com.example.eechat.entity.FileEntity;
import com.example.eechat.repository.FileEntityRepository;
import com.example.eechat.utils.Constants;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Optional;

@Setter
public class FileEntityRepositoryImpl implements FileEntityRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SneakyThrows
    public Optional<FileEntity> findByName(String name) {
        return Optional.of((FileEntity) entityManager
                .createQuery("SELECT v FROM FileEntity AS v WHERE v.name LIKE :name")
                .setParameter("name", name).getSingleResult());
    }

    @Override
    @SneakyThrows
    public String save(byte[] content, String name) {
        String placement = Constants.PLACE_FOLDER + name;
        File file = new File(placement);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(content);
        return placement;
    }

    @Override
    public void send(FileEntity fileEntity, byte[] content, ChannelEntity channelEntity) {
        fileEntity.setPlacement(save(content, fileEntity.getName()));
        entityManager.persist(fileEntity);
        channelEntity.getFileList().add(fileEntity);
        entityManager.merge(channelEntity);
    }

    @Override
    @SneakyThrows
    public byte[] download(String location) {
        FileInputStream input = new FileInputStream(location);
        return input.readAllBytes();
    }
}
