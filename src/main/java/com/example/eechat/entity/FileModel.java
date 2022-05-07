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
public class FileModel {

    @Id
    @Column(name = "file_ID")
    @NotNull
    private UUID id;

    @Column(name = "filename")
    @NotNull
    private String filename;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "text")
    @NotNull
    private String text;

    @Column(name = "time")
    @NotNull
    private Timestamp time;
}