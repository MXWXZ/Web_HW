package com.imwxz.store.controller;

import com.imwxz.store.entity.UserEntity;
import com.imwxz.store.service.IUserService;
import com.imwxz.store.util.HashUtil;
import com.imwxz.store.util.RetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokensController {
    @Autowired
    private IUserService user;

    @RequestMapping(value = "/api/tokens", method = RequestMethod.POST)
    public RetMessage signIn(@RequestParam("userName") String userName,
                             @RequestParam("userPassword") String userPassword) {
        RetMessage ret = new RetMessage();

        UserEntity obj = user.findUser(null, userName, null);
        if (obj != null && obj.getUserPassword().equals(HashUtil.sha256(userPassword))) {
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
