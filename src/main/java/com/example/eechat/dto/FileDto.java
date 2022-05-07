package com.example.eechat.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FileDto {

    private UUID id;

    private String username;

    private String name;

    private String text;

    private String channelName;

    private Timestamp time;
}
