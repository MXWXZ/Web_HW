package com.imwxz.store.controller;

import com.imwxz.store.entity.BookEntity;
import com.imwxz.store.service.IBookService;
import com.imwxz.store.util.RetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private IBookService book;

    @RequestMapping(value = "/api/books", method = RequestMethod.GET)
    public RetMessage getBooks(@RequestParam(value = "bookId", required = false) Integer bookId) {
        RetMessage ret = new RetMessage();
        if (bookId == null)
            ret.setData(book.findAll());
        else {
            BookEntity data = book.findBook(bookId);
            if (data == null) {
                ret.setCode(1);
                ret.setMsg("Book not exists!");
            }
            ret.setData(data);
        }

        return ret;
    }

    @RequestMapping(value = "/api/books", method = RequestMethod.DELETE)
    public RetMessage deleteBook(@RequestParam(value = "bookId") Integer bookId) {
        book.deleteBook(bookId);
        return new RetMessage();
    }
}
