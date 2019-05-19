package com.imwxz.store.controller;

import com.imwxz.store.service.IUserService;
import com.imwxz.store.util.RetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private IUserService user;

    @RequestMapping(value = "/api/userVerify", method = RequestMethod.GET)
    public RetMessage verifyUser(@RequestParam(value = "userId", required = false) Integer userId,
                                 @RequestParam(value = "userName", required = false) String userName,
                                 @RequestParam(value = "userEmail", required = false) String userEmail) {
        RetMessage ret = new RetMessage();

        if (userId != null || userName != null || userEmail != null) {
            if (user.findUser(userId, userName, userEmail) != null) {
                ret.setCode(1);
                ret.setMsg("User already exists!");
            }
        } else {
            ret.setCode(2);
            ret.setMsg("No condition exists!");
        }
        return ret;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public RetMessage signUp(@RequestParam("userEmail") String userEmail,
                             @RequestParam("userName") String userName,
                             @RequestParam("userPassword") String userPassword) {
        if (userPassword.length() < 6)
            return new RetMessage(3, "Password is too short!");
        if (user.findUser(null, null, userEmail) != null)
            return new RetMessage(1, "Email already exists!");
        if (user.findUser(null, userName, null) != null)
            return new RetMessage(2, "Username already exists!");

        user.addUser(userEmail, userName, userPassword);
        return new RetMessage();
    }
}