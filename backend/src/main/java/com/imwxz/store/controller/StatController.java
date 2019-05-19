package com.imwxz.store.controller;

import com.imwxz.store.entity.StatEntity;
import com.imwxz.store.service.IStatService;
import com.imwxz.store.util.RetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {
    @Autowired
    IStatService stat;

    @RequestMapping(value = "/api/stat", method = RequestMethod.GET)
    public RetMessage getUserStat(@RequestParam(value = "userId") Integer userId,
                                  @RequestParam(value = "minTime", required = false) Integer minTime,
                                  @RequestParam(value = "maxTime", required = false) Integer maxTime) {
        if ((minTime == null && maxTime != null) || (minTime != null && maxTime == null))
            return new RetMessage(2, "Time range error!");

        RetMessage ret = new RetMessage();
        StatEntity data;
        if (minTime != null)
            data = stat.getStat(userId, minTime, maxTime);
        else
            data = stat.getStat(userId);
        if (data == null) {
            ret.setCode(1);
            ret.setMsg("No records!");
        } else {
            ret.setData(data);
        }
        return ret;
    }
}
