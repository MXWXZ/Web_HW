package com.imwxz.store.controller;

import com.imwxz.store.dao.BookDao;
import com.imwxz.store.entity.BookEntity;
import com.imwxz.store.entity.MessageEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    public MessageEntity getBooks(@RequestParam(value = "bookId", required = false) Integer bookId) {
        BookDao books = new BookDao();
        MessageEntity ret = new MessageEntity();
        if (bookId == null)
            ret.setData(books.getAllBook());
        else {
            BookEntity data = books.getBookById(bookId);
            if (data == null) {
                ret.setCode(1);
                ret.setMsg("Book not exists!");
            }
            ret.setData(data);
        }

        return ret;
    }
}
