package com.example.eechat.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Table(name = "UserEntity")
@Entity
public class UserEntity {

    @Id
    @Column(name = "user_ID")
    @NotNull
    private UUID id;

    @Column(name = "username", unique = true)
    @NotNull
    private String username;

    @NotNull
    private Timestamp date;

    @ManyToMany(mappedBy = "users")
    private List<ChannelEntity> channelEntities;
}