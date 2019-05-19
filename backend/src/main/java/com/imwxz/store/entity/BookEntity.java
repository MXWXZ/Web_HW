package com.imwxz.store.entity;

import javax.persistence.*;

@Entity
@Table(name = "book", schema = "store")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private int bookId;

    @Column(name = "book_name", nullable = false, length = 256)
    private String bookName;

    @Column(name = "book_author", nullable = true, length = 256)
    private String bookAuthor;

    @Column(name = "book_price", nullable = false)
    private int bookPrice;

    @Column(name = "book_isbn", nullable = false, length = 32)
    private String bookIsbn;

    @Column(name = "book_amount", nullable = false)
    private int bookAmount;

    @Column(name = "book_img", nullable = true, length = 32)
    private String bookImg;

    @Column(name = "book_detail", nullable = true, length = 1024)
    private String bookDetail;

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
}
