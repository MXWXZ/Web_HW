package com.imwxz.store.service;

import com.imwxz.store.entity.BookEntity;

import java.util.List;

public interface IBookService {
    public BookEntity findBook(int bookId);

    public List<BookEntity> findAll();

    public void deleteBook(int bookId);

    public int addBook(String bookName, String bookAuthor, int bookPrice, String bookIsbn,
                       int bookAmount, String bookImg, String bookDetail);

    public int editBook(int bookId, String bookName, String bookAuthor, int bookPrice, String bookIsbn,
                        int bookAmount, String bookImg, String bookDetail);
}
