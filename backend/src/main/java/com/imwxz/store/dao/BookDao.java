package com.imwxz.store.dao;

import com.imwxz.store.entity.BookEntity;
import com.imwxz.store.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class BookDao {
    @SuppressWarnings("unchecked")
    public List<BookEntity> getAllBook() {
        Session session = HibernateUtil.openSession();
        return session.createQuery("FROM BookEntity").list();
    }
}
