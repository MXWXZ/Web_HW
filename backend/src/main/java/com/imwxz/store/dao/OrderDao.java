package com.imwxz.store.dao;

import com.imwxz.store.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class OrderDao {
    @SuppressWarnings("unchecked")
    public List<Object> getOrderWithUsername() {
        Session session = HibernateUtil.openSession();
        return session.createQuery("FROM OrderEntity inner join UserEntity").list();
    }
}
