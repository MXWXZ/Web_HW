package com.imwxz.store.service;

import com.imwxz.store.entity.UserEntity;

public interface IUserService {
    public UserEntity findUser(Integer id, String userName, String userEmail);

    public void addUser(String userEmail, String userName, String userPassword);
}
