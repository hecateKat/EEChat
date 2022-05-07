package com.example.eechat.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "MessageEntity")
public class MessageEntity implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "text")
    private String text;

    @Column(name = "user")
    private String user;

    @Column(name = "time")
    @NotNull
    private Timestamp time;

    @ElementCollection
    private List<String> users;
}