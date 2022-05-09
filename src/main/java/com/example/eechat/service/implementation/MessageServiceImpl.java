package com.example.eechat.service.implementation;

import com.example.eechat.model.ChannelModel;
import com.example.eechat.model.MessageModel;
import com.example.eechat.repository.adaptor.MessageAdaptor;
import com.example.eechat.repository.adaptor.MultipleChannelsAdaptor;
import com.example.eechat.service.MessageService;
import com.example.eechat.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class MessageServiceImpl implements MessageService {

    private MessageAdaptor messageAdaptor;

    private MultipleChannelsAdaptor channelsAdaptor;


    @Override
    public void send(MessageModel messageModel, String name) {
        save(messageModel, name);
        messageModel.setChannelName(name);
        produceConnection(messageModel);
    }

    @SneakyThrows
    private void save(MessageModel messageModel, String channel) {
        ChannelModel channelModel = channelsAdaptor.findByName(channel).orElseThrow(RuntimeException::new);
        messageAdaptor.send(messageModel, channelModel);
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
