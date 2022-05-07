package com.example.eechat.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDto {

    private UUID id;

    private String name;

    private List<UserDto> users;
}
