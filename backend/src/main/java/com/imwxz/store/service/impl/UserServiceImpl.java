package com.imwxz.store.service.impl;

import com.imwxz.store.entity.UserEntity;
import com.imwxz.store.repository.UserRepository;
import com.imwxz.store.service.IUserService;
import com.imwxz.store.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository user;

    @Override
    public UserEntity findUser(Integer id, String userName, String userEmail) {
        if (id != null)
            return user.findById(id).orElse(null);
        else if (userName != null)
            return user.findByUserName(userName);
        else if (userEmail != null)
            return user.findByUserEmail(userEmail);
        return null;
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
}
