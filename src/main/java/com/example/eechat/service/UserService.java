package com.example.eechat.service;

public interface UserService {

    void addUserToChat(String name);

    void isActive(String name);

    void delete(String name);
}
