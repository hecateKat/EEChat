package com.example.eechat.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Builder
public class FileModel {

    private UUID id;

    private String username;

    private String name;

    private String placement;

    private byte[] text;

    private Timestamp time;
}
