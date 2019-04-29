package com.imwxz.store.dao;

import com.imwxz.store.entity.StatEntity;
import com.imwxz.store.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Timestamp;

public class StatDao {
    @SuppressWarnings("unchecked")
    public StatEntity getUserStat(int userId) {
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("select count(*),sum((select sum(orderAmount) from OrderItemEntity as oi where oi.orderId=o.orderId))," +
                "sum(orderPrice),min(orderTime),max(orderTime) from OrderEntity o where userId = ?1");
        query.setParameter(1, userId);
        Object[] res = (Object[]) query.list().get(0);
        System.out.println(res[4]);
        session.close();

        if (res[2] == null || res[3] == null || res[4] == null)
            return null;

        StatEntity ret = new StatEntity();
        ret.setTotOrders(Math.toIntExact((long) res[0]));
        ret.setTotBooks(Math.toIntExact((long) res[1]));
        ret.setTotMoney(Math.toIntExact((long) res[2]));
        ret.setMinTime((Timestamp) res[3]);
        ret.setMaxTime((Timestamp) res[4]);
        return ret;
    }
}
