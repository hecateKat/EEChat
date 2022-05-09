package com.example.eechat.service.implementation;

import com.example.eechat.model.ChannelModel;
import com.example.eechat.model.FileModel;
import com.example.eechat.model.MessageModel;
import com.example.eechat.model.UserModel;
import com.example.eechat.repository.adaptor.FileAdaptor;
import com.example.eechat.repository.adaptor.MultipleChannelsAdaptor;
import com.example.eechat.service.FileService;
import com.example.eechat.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class FileServiceImpl implements FileService {

    private final FileAdaptor fileAdaptor;

    private final MultipleChannelsAdaptor channelsAdaptor;


    @Override
    public FileModel findByName(String name, String username, String channel) {
        FileModel fileModel = fileAdaptor.findByName(channel).orElseThrow(RuntimeException::new);
        if (checksIfUSerIsOnChat(getChannelmodel(username), username))
            fileModel.setText(fileAdaptor.getText(fileModel.getPlacement()));
        return fileModel;
    }

    @Override
    public void send(String name, String username, byte[] text, String channel) {
        save(name, username, text, channel);
        produceConnection(messageFactory(name, username, channel));
    }

    private MessageModel messageFactory(String name, String username, String channel) {
        MessageModel messageModel = MessageModel.builder()
                .username(username)
                .time(Timestamp.from(Instant.now()))
                .text(name)
                .channelName(channel)
                .build();
        return messageModel;
    }

    private boolean checksIfUSerIsOnChat(ChannelModel channelmodel, String name){
        return channelmodel
                .getUsers()
                .stream()
                .map(UserModel::getUsername)
                .collect(Collectors.toList())
                .contains(name);
    }

    private ChannelModel getChannelmodel(String username) {
        return channelsAdaptor.findByName(username).orElseThrow(RuntimeException::new);
    }

    @SneakyThrows
    private void save(String name, String username, byte[] text, String channelName){
        if (checksIfUSerIsOnChat(getChannelmodel(username), username)){
            fileAdaptor.send(fileFactory(name, username, text), getChannelmodel(username));
        }
    }

    private FileModel fileFactory(String name, String username, byte[] text) {
        FileModel fileModel = FileModel.builder()
                .id(UUID.randomUUID())
                .name(name)
                .username(username)
                .text(text)
                .time(Timestamp.from(Instant.now()))
                .build();
        return fileModel;
    }

    @SneakyThrows
    private void produceConnection(MessageModel messageModel) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Constants.URL);
        Session session = connectionFactory
                .createConnection()
                .createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session
                .createProducer(session.createTopic(messageModel
                        .getChannelName()));
        producer
                .send(session
                        .createTextMessage(messageModel
                                .toString()));
    }
}