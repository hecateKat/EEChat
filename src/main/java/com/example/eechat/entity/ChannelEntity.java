package com.example.eechat.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ChannelEntity")
@EqualsAndHashCode
public class ChannelEntity {

    @Id
    @GeneratedValue
    @Column(name = "channel_ID")
    @NotNull
    private UUID id;

    @Column(name = "channel_NAME")
    private String name;

    @ManyToMany
    @JoinTable(name = "users", joinColumns = @JoinColumn(name = "channel_ID"), inverseJoinColumns = @JoinColumn(name = "user_ID"))
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "channel_message", joinColumns = @JoinColumn(name = "channel_ID"),
            inverseJoinColumns = @JoinColumn(name = "message_id")
    )
    private List<MessageEntity> messages = new ArrayList<>();

    @OneToMany
    private List<FileEntity> fileList = new ArrayList<>();

}