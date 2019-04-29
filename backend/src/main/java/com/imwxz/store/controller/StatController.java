package com.imwxz.store.controller;

import com.imwxz.store.dao.StatDao;
import com.imwxz.store.entity.MessageEntity;
import com.imwxz.store.entity.StatEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {
    @RequestMapping(value = "/api/stat", method = RequestMethod.GET)
    public MessageEntity getUserStat(@RequestParam(value = "userId") Integer userId) {
        StatDao stat = new StatDao();
        MessageEntity ret = new MessageEntity();
        StatEntity data = stat.getUserStat(userId);
        if (data == null) {
            ret.setCode(1);
            ret.setMsg("UserId do not exists!");
        } else {
            ret.setData(data);
        }
        return ret;
    }
}
