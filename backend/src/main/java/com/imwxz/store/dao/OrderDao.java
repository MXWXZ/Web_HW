package com.imwxz.store.dao;

import com.imwxz.store.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDao {
    @SuppressWarnings("unchecked")
    public List getAllOrderWithUsername() {
        Session session = HibernateUtil.openSession();
        List<Object[]> res = session.createQuery("select orderId,o.userByUserId.userName,orderStatus from OrderEntity o").list();
        List ret = new ArrayList();
        for (Object[] i : res) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", i[0]);
            map.put("userName", i[1]);
            map.put("orderStatus", i[2]);
            ret.add(map);
        }
        return ret;
    }
}
