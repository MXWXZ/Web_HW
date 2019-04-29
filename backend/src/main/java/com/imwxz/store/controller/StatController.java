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
    public MessageEntity getUserStat(@RequestParam(value = "userId") Integer userId,
                                     @RequestParam(value = "minTime", required = false) Integer minTime,
                                     @RequestParam(value = "maxTime", required = false) Integer maxTime) {
        if ((minTime == null && maxTime != null) || (minTime != null && maxTime == null))
            return new MessageEntity(2, "Time range error!");

        StatDao stat = new StatDao();
        MessageEntity ret = new MessageEntity();
        StatEntity data;
        if (minTime != null)
            data = stat.getUserStatRange(userId, minTime, maxTime);
        else
            data = stat.getUserStat(userId);
        if (data == null) {
            ret.setCode(1);
            ret.setMsg("No records!");
        } else {
            ret.setData(data);
        }
        return ret;
    }
}
