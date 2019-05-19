package com.imwxz.store.service.impl;

import com.imwxz.store.entity.StatEntity;
import com.imwxz.store.repository.OrderRepository;
import com.imwxz.store.service.IStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatServiceImpl implements IStatService {
    @Autowired
    OrderRepository order;

    @Override
    public StatEntity getStat(int userId) {
        return retResult(order.getStat(userId).get(0));
    }


    public StatEntity getStat(int userId, int minTime, int maxTime) {
        return retResult(order.getStat(userId, minTime, maxTime).get(0));
    }

    private StatEntity retResult(Object[] res) {
        if (res[2] == null || res[3] == null || res[4] == null)
            return null;

        StatEntity ret = new StatEntity();
        ret.setTotOrders(Math.toIntExact((long) res[0]));
        ret.setTotBooks(Math.toIntExact((long) res[1]));
        ret.setTotMoney(Math.toIntExact((long) res[2]));
        ret.setMinTime(Math.toIntExact((long) res[3]));
        ret.setMaxTime(Math.toIntExact((long) res[4]));
        return ret;
    }
}
