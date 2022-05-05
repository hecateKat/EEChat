package com.example.eechat.entity;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ChannelEntity")
@EqualsAndHashCode(of = "name")
@NamedQuery(name = ChannelEntity.FIND, query = "SELECT v FROM ChannelEntity v WHERE v.name = :name")
public class ChannelEntity {

    public static final String FIND = "findOne";

    @Id
    private String name;

    private boolean isPrivate;

    @ElementCollection
    private List<String> channelMessages;

    @ElementCollection
    private List<String> userNames;
}
