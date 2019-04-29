package com.imwxz.store.dao;

import com.imwxz.store.entity.BookEntity;
import com.imwxz.store.entity.CartEntity;
import com.imwxz.store.entity.OrderEntity;
import com.imwxz.store.entity.OrderItemEntity;
import com.imwxz.store.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
        session.close();
        return ret;
    }

    @SuppressWarnings("unchecked")
    public List getOrderByUserId(int userId) {
        Session session = HibernateUtil.openSession();
        List<Object[]> res = session.createQuery("select orderId,orderStatus,unix_timestamp(orderTime) from OrderEntity o").list();
        List ret = new ArrayList();
        for (Object[] i : res) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", i[0]);
            map.put("orderStatus", i[1]);
            map.put("orderTime", i[2]);
            ret.add(map);
        }
        session.close();
        return ret;
    }

    @SuppressWarnings("unchecked")
    public List getOrderItemByOrderId(int orderId) {
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("select o.bookByBookId.bookId,o.bookByBookId.bookName," +
                "o.bookByBookId.bookPrice,orderAmount from OrderItemEntity o where orderId = ?1");
        query.setParameter(1, orderId);
        List<Object[]> res = query.list();
        List ret = new ArrayList();
        for (Object[] i : res) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("bookId", i[0]);
            map.put("bookName", i[1]);
            map.put("bookPrice", i[2]);
            map.put("orderAmount", i[3]);
            ret.add(map);
        }
        session.close();
        return ret;
    }

    public int addOrder(int userId, List<Integer> cartId) {
        if (cartId.size() == 0)
            return 4;
        CartDao cart = new CartDao();
        List<CartEntity> res = cart.getAllCartByUserId(userId);
        if (res.size() == 0)
            return 1;

        int ret = 0;
        Session session = HibernateUtil.openSession();
        try {
            session.beginTransaction();
            OrderEntity order = new OrderEntity();
            order.setUserId(userId);
            order.setOrderStatus(1);
            ret = (Integer) session.save(order);
            ret = ret == 0 ? -1 : 0;
            for (Integer i : cartId) {
                boolean flag = false;
                for (CartEntity j : res) {
                    if (j.getCartId() == i) {
                        OrderItemEntity item = new OrderItemEntity();
                        item.setOrderId(order.getOrderId());
                        item.setBookId(j.getBookId());
                        item.setOrderAmount(j.getCartAmount());
                        int ret2 = (Integer) session.save(item);
                        ret = ret2 == 0 ? -1 : ret;

                        BookEntity book = j.getBookByBookId();
                        if (book.getBookAmount() < j.getCartAmount()) {
                            session.getTransaction().rollback();
                            session.close();
                            return 2;
                        }
                        book.setBookAmount(book.getBookAmount() - j.getCartAmount());
                        session.update(book);
                        session.delete(j);

                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    session.getTransaction().rollback();
                    session.close();
                    return 3;
                }
            }
            session.getTransaction().commit();
            session.close();
            return 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            return -1;
        }
    }
}
