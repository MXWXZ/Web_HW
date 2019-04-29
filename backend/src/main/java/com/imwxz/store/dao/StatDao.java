package com.imwxz.store.dao;

import com.imwxz.store.entity.StatEntity;
import com.imwxz.store.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class StatDao {
    @SuppressWarnings("unchecked")
    public StatEntity getUserStat(int userId) {
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("select count(*),sum((select sum(orderAmount) from OrderItemEntity as oi where oi.orderId=o.orderId))," +
                "sum(orderPrice),unix_timestamp(min(orderTime)),unix_timestamp(max(orderTime)) from OrderEntity o where userId = ?1");
        query.setParameter(1, userId);
        Object[] res = (Object[]) query.list().get(0);
        session.close();

        return retResult(res);
    }

    @SuppressWarnings("unchecked")
    public StatEntity getUserStatRange(int userId, int minTime, int maxTime) {
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("select count(*),sum((select sum(orderAmount) from OrderItemEntity as oi where oi.orderId=o.orderId))," +
                "sum(orderPrice),unix_timestamp(min(orderTime)),unix_timestamp(max(orderTime)) from OrderEntity o where userId = ?1 " +
                "and orderTime between FROM_UNIXTIME(?2) and FROM_UNIXTIME(?3)");
        query.setParameter(1, userId);
        query.setParameter(2, minTime);
        query.setParameter(3, maxTime);
        Object[] res = (Object[]) query.list().get(0);
        session.close();

        return retResult(res);
    }

    public StatEntity retResult(Object[] res) {
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
