package com.imwxz.store.service.impl;

import com.imwxz.store.entity.UserEntity;
import com.imwxz.store.repository.UserRepository;
import com.imwxz.store.service.IUserService;
import com.imwxz.store.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository user;

    @Override
    public UserEntity findUser(Integer userId, String userName, String userEmail) {
        if (userId != null)
            return user.findById(userId).orElse(null);
        else if (userName != null)
            return user.findByUserName(userName);
        else if (userEmail != null)
            return user.findByUserEmail(userEmail);
        return null;
    }

    @Override
    public List<UserEntity> findAllUser() {
        return user.findAll();
    }

    @Transactional
    @Override
    public void addUser(String userEmail, String userName, String userPassword) {
        UserEntity obj = new UserEntity();
        obj.setUserName(userName);
        obj.setUserEmail(userEmail);
        obj.setUserPassword(HashUtil.sha256(userPassword));
        user.save(obj);
    }

    @Override
    public int freezeUser(int userId, Integer userStatus) {
        UserEntity obj = user.findById(userId).orElse(null);
        if (obj == null)
            return 1;

        obj.setUserStatus(userStatus);
        user.save(obj);
        return 0;
    }
}
