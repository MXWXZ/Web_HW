package com.imwxz.store.service.impl;

import com.imwxz.store.repository.StatRepository;
import com.imwxz.store.service.IStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements IStatService {
    @Autowired
    StatRepository stat;

    @Override
    public Map<String, Object> getStat(int userId) {
        return statResult(stat.getStat(userId).get(0));
    }


    public Map<String, Object> getStat(int userId, int minTime, int maxTime) {
        return statResult(stat.getStat(userId, minTime, maxTime).get(0));
    }

    private Map<String, Object> statResult(Object[] res) {
        if (res[2] == null || res[3] == null || res[4] == null)
            return null;

        Map<String, Object> ret = new HashMap<>();
        ret.put("totOrders", (Math.toIntExact((long) res[0])));
        ret.put("totBooks", (Math.toIntExact((long) res[1])));
        ret.put("totMoney", (Math.toIntExact((long) res[2])));
        ret.put("minTime", (Math.toIntExact((long) res[3])));
        ret.put("maxTime", (Math.toIntExact((long) res[4])));
        return ret;
    }

    @Override
    public Map<String, Object> getTotalStat() {
        Object[] res = stat.getTotalStat().get(0);
        if (res[0] == null || res[1] == null)
            return null;

        Map<String, Object> ret = new HashMap<>();
        ret.put("minTime", (Math.toIntExact((long) res[0])));
        ret.put("maxTime", (Math.toIntExact((long) res[1])));
        return ret;
    }

    @Override
    public List getBookStat(int minTime, int maxTime) {
        List<Object[]> res = stat.getBookStat(minTime, maxTime);
        List<Object> ret = new ArrayList<>();
        for (Object[] i : res) {
            Map<String, Object> map = new HashMap<>();
            map.put("bookName", i[0]);
            map.put("bookAmount", i[1]);
            map.put("bookPrice", i[2]);
            ret.add(map);
        }
        return ret.isEmpty() ? null : ret;
    }

    @Override
    public List getUserStat(int minTime, int maxTime) {
        List<Object[]> res = stat.getUserStat(minTime, maxTime);
        List<Object> ret = new ArrayList<>();
        for (Object[] i : res) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId", i[0]);
            map.put("userName", i[1]);
            map.put("bookAmount", i[2]);
            map.put("totPrice", i[3]);
            ret.add(map);
        }
        return ret.isEmpty() ? null : ret;
    }
}
