package com.imwxz.store.controller;

import com.imwxz.store.dao.BookDao;
import com.imwxz.store.entity.MessageEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    public MessageEntity getBooks() {
        BookDao books = new BookDao();
        MessageEntity ret = new MessageEntity();
        ret.setData(books.getAllBook());
        return ret;
    }
}
