package com.imwxz.store.controller;

import com.imwxz.store.service.IStatService;
import com.imwxz.store.util.RetMessage;
import com.imwxz.store.util.jwt.AdminToken;
import com.imwxz.store.util.jwt.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StatController {
    @Autowired
    IStatService stat;

    @UserToken
    @GetMapping(value = "/api/stat")
    public RetMessage getUserStat(@RequestParam(value = "userId") Integer userId,
                                  @RequestParam(value = "minTime", required = false) Integer minTime,
                                  @RequestParam(value = "maxTime", required = false) Integer maxTime) {
        if ((minTime == null && maxTime != null) || (minTime != null && maxTime == null))
            return new RetMessage(2, "Time range error!");

        RetMessage ret = new RetMessage();
        Map<String, Object> data;
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

    @AdminToken
    @GetMapping(value = "/api/bookstat")
    public RetMessage getBookStat(@RequestParam(value = "minTime") Integer minTime,
                                  @RequestParam(value = "maxTime") Integer maxTime) {
        RetMessage ret = new RetMessage();
        List data = stat.getBookStat(minTime, maxTime);
        if (data == null) {
            ret.setCode(1);
            ret.setMsg("No records!");
        } else {
            ret.setData(data);
        }
        return ret;
    }

    @AdminToken
    @GetMapping(value = "/api/userstat")
    public RetMessage getUserStat(@RequestParam(value = "minTime") Integer minTime,
                                  @RequestParam(value = "maxTime") Integer maxTime) {
        RetMessage ret = new RetMessage();
        List data = stat.getUserStat(minTime, maxTime);
        if (data == null) {
            ret.setCode(1);
            ret.setMsg("No records!");
        } else {
            ret.setData(data);
        }
        return ret;
    }

    @AdminToken
    @GetMapping(value = "/api/totstat")
    public RetMessage getTotStat() {
        RetMessage ret = new RetMessage();
        Map<String, Object> data = stat.getTotalStat();
        if (data == null) {
            ret.setCode(1);
            ret.setMsg("No records!");
        } else {
            ret.setData(data);
        }
        return ret;
    }
}
