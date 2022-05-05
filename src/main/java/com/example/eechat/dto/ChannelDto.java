package com.example.eechat.dto;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelDto {

    private String name;

    private boolean isPrivate;

    private List<String> channelMessages;

    private List<String> userNames;
}
