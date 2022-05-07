package com.example.eechat.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ChannelUserDto {

    private String username;

    private String channelName;
}
