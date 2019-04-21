package com.imwxz.store.controller;

import com.imwxz.store.dao.UserDao;
import com.imwxz.store.entity.MessageEntity;
import com.imwxz.store.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokensController {
    @RequestMapping(value = "/api/tokens", method = RequestMethod.POST)
    public MessageEntity signIn(@RequestParam("userName") String userName,
                                @RequestParam("userPassword") String userPassword) {
        UserDao user = new UserDao();
        MessageEntity ret = new MessageEntity();

        UserEntity obj = user.signIn(userName, userPassword);
        if (obj != null) {
            if (obj.getUserStatus() == 1) {
                ret.setCode(2);
                ret.setMsg("Your account has been frozen!");
            } else {
                ret.setData(obj);
            }
        } else {
            ret.setCode(1);
            ret.setMsg("Username or password is invalid!");
        }
        return ret;
    }
}
