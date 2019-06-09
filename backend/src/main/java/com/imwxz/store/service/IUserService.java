package com.imwxz.store.service;

import com.imwxz.store.entity.UserEntity;

import java.util.List;

public interface IUserService {
    public UserEntity findUser(Integer userId, String userName, String userEmail);

    public List<UserEntity> findAllUser();

    public void addUser(String userEmail, String userName, String userPassword);

    public int freezeUser(int userId, Integer userStatus);
}
