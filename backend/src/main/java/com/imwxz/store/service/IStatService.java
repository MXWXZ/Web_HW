package com.imwxz.store.service;

import com.imwxz.store.entity.StatEntity;

public interface IStatService {
    public StatEntity getStat(int userId);

    public StatEntity getStat(int userId, int minTime, int maxTime);
}
