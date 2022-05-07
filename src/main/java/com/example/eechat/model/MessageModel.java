package com.example.eechat.model;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageModel implements Serializable {

    private UUID id;

    private String channelName;

    private String username;

    private String text;

    private Timestamp time;

    private List<String> users;
}