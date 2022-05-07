package com.example.eechat.model;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private UUID id;

    private String username;

    private Timestamp time;
}
