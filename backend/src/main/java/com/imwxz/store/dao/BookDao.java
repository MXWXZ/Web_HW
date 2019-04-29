package com.imwxz.store.dao;

import com.imwxz.store.entity.BookEntity;
import com.imwxz.store.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class BookDao {
    @SuppressWarnings("unchecked")
    public List<BookEntity> getAllBook() {
        Session session = HibernateUtil.openSession();
        List ret = session.createQuery("FROM BookEntity").list();
        session.close();
        return ret;
    }

    public BookEntity getBookById(int bookId) {
        Session session = HibernateUtil.openSession();
        BookEntity ret = session.get(BookEntity.class, bookId);
        session.close();
        return ret;
    }
}
