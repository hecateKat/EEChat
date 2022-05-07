package com.example.eechat.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MessageDto {

    private String username;

    private String text;

    private String channelName;

    private Timestamp time;
}
