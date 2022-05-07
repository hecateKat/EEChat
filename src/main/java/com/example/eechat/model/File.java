package com.example.eechat.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
public class File {

    private UUID id;

    private String username;

    private String fileName;

    private String location;

    private byte[] content;

    private Timestamp time;
}
