package com.imwxz.store.service.impl;

import com.imwxz.store.entity.BookEntity;
import com.imwxz.store.repository.BookRepository;
import com.imwxz.store.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    BookRepository book;

    @Override
    public BookEntity findBook(int bookId) {
        return book.findById(bookId).orElse(null);
    }

    @Override
    public List<BookEntity> findAll() {
        return book.findAll();
    }
}
