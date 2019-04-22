package com.imwxz.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class BookEntity {
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private int bookPrice;
    private String bookIsbn;
    private int bookAmount;
    private String bookImg;
    private String bookDetail;
    @JsonIgnore
    private Set<OrderItemEntity> orderItemsByBookId;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public int getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(int bookAmount) {
        this.bookAmount = bookAmount;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(String bookDetail) {
        this.bookDetail = bookDetail;
    }

    public Set<OrderItemEntity> getOrderItemsByBookId() {
        return orderItemsByBookId;
    }

    public void setOrderItemsByBookId(Set<OrderItemEntity> orderItemsByBookId) {
        this.orderItemsByBookId = orderItemsByBookId;
    }
}
