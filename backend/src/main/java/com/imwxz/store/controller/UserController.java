package com.imwxz.store.controller;

import com.imwxz.store.dao.UserDao;
import com.imwxz.store.entity.MessageEntity;
import com.imwxz.store.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @RequestMapping(value = "/api/allusers", method = RequestMethod.GET)
    public MessageEntity getAllUser() {
        UserDao user = new UserDao();
        MessageEntity ret = new MessageEntity();
        ret.setData(user.getAllUser());
        return ret;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public List<UserEntity> getUser(@RequestParam(value = "userId", required = false) Integer userId,
                                    @RequestParam(value = "userName", defaultValue = "") String userName,
                                    @RequestParam(value = "userEmail", defaultValue = "") String userEmail) {
        UserDao user = new UserDao();
        if (userId != null)
            return user.getUserById(userId);
        else if (!userName.isEmpty())
            return user.getUserByName(userName);
        else if (!userEmail.isEmpty())
            return user.getUserByEmail(userEmail);
        return new ArrayList<UserEntity>();
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public MessageEntity signUp(@RequestParam("userEmail") String userEmail,
                                @RequestParam("userName") String userName,
                                @RequestParam("userPassword") String userPassword) {
        UserDao user = new UserDao();
        if (!user.getUserByEmail(userEmail).isEmpty())
            return new MessageEntity(1, "Email already exists!");
        if (!user.getUserByName(userName).isEmpty())
            return new MessageEntity(2, "Username already exists!");
        if (userPassword.length() < 6)
            return new MessageEntity(3, "Password is too short!");

        MessageEntity ret = new MessageEntity();
        ret.setCode(user.signUp(userEmail, userName, userPassword));
        if (ret.getCode() == -1)
            ret.setMsg("Unknown error!");

        return ret;
    }
}