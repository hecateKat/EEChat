package com.example.eechat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageEntity implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    private String text;

    private byte[] file;
}
