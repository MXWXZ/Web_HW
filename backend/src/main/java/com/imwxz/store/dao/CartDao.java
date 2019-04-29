package com.imwxz.store.dao;

import com.imwxz.store.entity.CartEntity;
import com.imwxz.store.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartDao {
    @SuppressWarnings("unchecked")
    public List getCartByUserId(int userId) {
        Session session = HibernateUtil.openSession();
        List<Object[]> res = session.createQuery("select c.cartId,c.bookByBookId.bookId," +
                "c.bookByBookId.bookName,c.bookByBookId.bookImg,c.bookByBookId.bookAmount," +
                "c.bookByBookId.bookPrice,cartAmount from CartEntity c").list();
        List ret = new ArrayList();
        for (Object[] i : res) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cartId", i[0]);
            map.put("bookId", i[1]);
            map.put("bookName", i[2]);
            map.put("bookImg", i[3]);
            map.put("bookAmount", i[4]);
            map.put("bookPrice", i[5]);
            map.put("cartAmount", i[6]);
            ret.add(map);
        }
        session.close();
        return ret;
    }

    @SuppressWarnings("unchecked")
    public List<CartEntity> getAllCartByUserId(int userId) {
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("from CartEntity c join fetch c.bookByBookId where c.userId = ?1");
        query.setParameter(1, userId);
        List ret = query.list();
        session.close();
        return ret;
    }

    private CartEntity getCartEntity(Session session, int userId, int bookId) {
        Query query = session.createQuery("from CartEntity where userId = ?1 and bookId = ?2");
        query.setParameter(1, userId);
        query.setParameter(2, bookId);
        List ret = query.list();
        return ret.isEmpty() ? null : (CartEntity) ret.get(0);
    }

    public int editCart(int userId, int bookId, int cartAmount) {
        Session session = HibernateUtil.openSession();
        CartEntity former = getCartEntity(session, userId, bookId);
        if (former == null)
            return 1;

        try {
            session.beginTransaction();
            former.setCartAmount(cartAmount);
            session.update(former);
            session.getTransaction().commit();
            session.close();
            return 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            return -1;
        }
    }

    public int addCart(int userId, int bookId, int cartAmount) {
        Session session = HibernateUtil.openSession();
        CartEntity former = getCartEntity(session, userId, bookId);

        try {
            session.beginTransaction();
            int ret = 0;
            if (former == null) {
                CartEntity cart = new CartEntity();
                cart.setBookId(bookId);
                cart.setUserId(userId);
                cart.setCartAmount(cartAmount);
                ret = (Integer) session.save(cart);
                ret = ret == 0 ? -1 : 0;
            } else {
                former.setCartAmount(former.getCartAmount() + cartAmount);
                session.update(former);
            }
            session.getTransaction().commit();
            session.close();
            return ret;
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.close();
            return -1;
        }
    }

    public int deleteCart(int userId, int bookId) {
        Session session = HibernateUtil.openSession();
        CartEntity req = getCartEntity(session, userId, bookId);
        if (req == null) {
            return 1;
        } else {
            try {
                session.beginTransaction();
                session.delete(req);
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
}
