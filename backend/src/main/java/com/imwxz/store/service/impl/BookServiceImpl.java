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

    @Override
    public void deleteBook(int bookId) {
        book.deleteById(bookId);
    }

    @Override
    public int addBook(String bookName, String bookAuthor, int bookPrice, String bookIsbn,
                       int bookAmount, String bookImg, String bookDetail) {
        BookEntity obj = new BookEntity();
        obj.setBookName(bookName);
        obj.setBookAuthor(bookAuthor);
        obj.setBookPrice(bookPrice);
        obj.setBookIsbn(bookIsbn);
        obj.setBookAmount(bookAmount);
        obj.setBookImg(bookImg);
        obj.setBookDetail(bookDetail);
        return book.save(obj).getBookId();
    }

    @Override
    public int editBook(int bookId, String bookName, String bookAuthor, int bookPrice, String bookIsbn,
                        int bookAmount, String bookImg, String bookDetail) {
        BookEntity obj = findBook(bookId);
        if (obj == null)
            return 1;
        obj.setBookName(bookName);
        obj.setBookAuthor(bookAuthor);
        obj.setBookPrice(bookPrice);
        obj.setBookIsbn(bookIsbn);
        obj.setBookAmount(bookAmount);
        obj.setBookImg(bookImg);
        obj.setBookDetail(bookDetail);
        book.save(obj);
        return 0;
    }
}
