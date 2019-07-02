package com.imwxz.store.service;

import java.util.List;
import java.util.Map;

public interface IStatService {
    public Map<String, Object> getStat(int userId);

    public Map<String, Object> getStat(int userId, int minTime, int maxTime);

    public Map<String, Object> getTotalStat();

    public List getBookStat(int minTime, int maxTime);

    public List getUserStat(int minTime, int maxTime);
}
