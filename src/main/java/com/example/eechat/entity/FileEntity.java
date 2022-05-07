package com.example.eechat.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@Table(name = "FileEntity")
@Entity
public class FileEntity {

    @Id
    @Column(name = "file_ID")
    @NotNull
    private UUID id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "placement")
    @NotNull
    private String placement;

    @Column(name = "time")
    @NotNull
    private Timestamp time;
}