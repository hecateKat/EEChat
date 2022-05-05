package com.example.eechat.entity;

import lombok.*;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {

    @Id
    private Long id;

    private String userName;

}
