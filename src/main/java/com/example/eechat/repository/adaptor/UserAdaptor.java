package com.example.eechat.repository.adaptor;

import com.example.eechat.mapper.UserEntityMapper;
import com.example.eechat.model.UserModel;
import com.example.eechat.repository.UserEntityRepository;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor(onConstructor_ = @Inject)
@Transactional
public class UserAdaptor {

    private UserEntityRepository userEntityRepository;

    public void addUserToChat(UserModel userModel){
        userEntityRepository.saveEntity(UserEntityMapper.toEntity(userModel));
    }

    Optional<UserModel> findByName(String name){
        return userEntityRepository.findByName(name).map(UserEntityMapper::toModel);
    }
}
