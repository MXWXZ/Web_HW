package com.imwxz.store.controller;

import com.imwxz.store.entity.BookEntity;
import com.imwxz.store.service.IBookService;
import com.imwxz.store.util.RetMessage;
import com.imwxz.store.util.jwt.AdminToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private IBookService book;

    @GetMapping(value = "/api/books")
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

    @AdminToken
    @PostMapping(value = "/api/books")
    public RetMessage addBook(@RequestParam(value = "bookName") String bookName,
                              @RequestParam(value = "bookAuthor") String bookAuthor,
                              @RequestParam(value = "bookPrice") int bookPrice,
                              @RequestParam(value = "bookIsbn") String bookIsbn,
                              @RequestParam(value = "bookAmount") int bookAmount,
                              @RequestParam(value = "bookImg") String bookImg,
                              @RequestParam(value = "bookDetail") String bookDetail) {
        RetMessage ret = new RetMessage();
        if (book.addBook(bookName, bookAuthor, bookPrice, bookIsbn, bookAmount, bookImg, bookDetail) <= 0) {
            ret.setCode(1);
            ret.setMsg("Database error!");
        }
        return ret;
    }

    @AdminToken
    @PutMapping(value = "/api/books")
    public RetMessage editBook(@RequestParam(value = "bookId") int bookId,
                               @RequestParam(value = "bookName") String bookName,
                               @RequestParam(value = "bookAuthor") String bookAuthor,
                               @RequestParam(value = "bookPrice") int bookPrice,
                               @RequestParam(value = "bookIsbn") String bookIsbn,
                               @RequestParam(value = "bookAmount") int bookAmount,
                               @RequestParam(value = "bookImg") String bookImg,
                               @RequestParam(value = "bookDetail") String bookDetail) {
        RetMessage ret = new RetMessage();
        if (book.editBook(bookId, bookName, bookAuthor, bookPrice, bookIsbn, bookAmount, bookImg, bookDetail) != 0) {
            ret.setCode(1);
            ret.setMsg("Book do not exist!");
        }
        return ret;
    }

    @AdminToken
    @DeleteMapping(value = "/api/books")
    public RetMessage deleteBook(@RequestParam(value = "bookId") Integer bookId) {
        book.deleteBook(bookId);
        return new RetMessage();
    }
}
