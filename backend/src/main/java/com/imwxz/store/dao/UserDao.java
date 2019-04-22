package com.imwxz.store.dao;

import com.imwxz.store.entity.UserEntity;
import com.imwxz.store.util.HashUtil;
import com.imwxz.store.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDao {
    public UserEntity signIn(String userName, String userPassword) {
        List<UserEntity> user = getUserByName(userName);
        if (user.isEmpty())
            return null;
        if (!Objects.equals(HashUtil.sha256(userPassword), user.get(0).getUserPassword()))
            return null;
        return user.get(0);
    }

    public int signUp(String userEmail, String userName, String userPassword) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            UserEntity user = new UserEntity();
            user.setUserEmail(userEmail);
            user.setUserName(userName);
            user.setUserPassword(HashUtil.sha256(userPassword));
            int ret = (Integer) session.save(user);
            tx.commit();
            session.close();
            return ret;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            return 0;
        }
    }

    @SuppressWarnings("unchecked")
    public List<UserEntity> getAllUser() {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            List<UserEntity> ret = session.createQuery("from UserEntity").list();
            tx.commit();
            session.close();
            return ret;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            return new ArrayList<UserEntity>();
        }
    }

    public List<UserEntity> getUserById(int userId) {
        return getUserWithParam("from UserEntity where userId = ?1", String.valueOf(userId));
    }

    public List<UserEntity> getUserByName(String userName) {
        return getUserWithParam("from UserEntity where userName = ?1", userName);
    }

    public List<UserEntity> getUserByEmail(String userEmail) {
        return getUserWithParam("from UserEntity where userEmail = ?1", userEmail);
    }

    @SuppressWarnings("unchecked")
    public List<UserEntity> getUserWithParam(String hql, String param) {
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery(hql);
        query.setParameter(1, param);
        return query.list();
    }
}
