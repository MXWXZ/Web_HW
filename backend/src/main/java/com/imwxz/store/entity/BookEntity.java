package com.imwxz.store.entity;

import javax.persistence.*;

@Entity
@Table(name = "book", schema = "store", catalog = "")
public class BookEntity {
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private int bookPrice;
    private String bookIsbn;
    private int bookAmount;
    private String bookImg;
    private String bookDetail;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "book_name", nullable = false, length = 256)
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "book_author", nullable = true, length = 256)
    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Basic
    @Column(name = "book_price", nullable = false)
    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Basic
    @Column(name = "book_isbn", nullable = false, length = 32)
    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    @Basic
    @Column(name = "book_amount", nullable = false)
    public int getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(int bookAmount) {
        this.bookAmount = bookAmount;
    }

    @Basic
    @Column(name = "book_img", nullable = true, length = 32)
    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    @Basic
    @Column(name = "book_detail", nullable = true, length = 1024)
    public String getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(String bookDetail) {
        this.bookDetail = bookDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bookId != that.bookId) return false;
        if (bookPrice != that.bookPrice) return false;
        if (bookAmount != that.bookAmount) return false;
        if (bookName != null ? !bookName.equals(that.bookName) : that.bookName != null) return false;
        if (bookAuthor != null ? !bookAuthor.equals(that.bookAuthor) : that.bookAuthor != null) return false;
        if (bookIsbn != null ? !bookIsbn.equals(that.bookIsbn) : that.bookIsbn != null) return false;
        if (bookImg != null ? !bookImg.equals(that.bookImg) : that.bookImg != null) return false;
        if (bookDetail != null ? !bookDetail.equals(that.bookDetail) : that.bookDetail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (bookAuthor != null ? bookAuthor.hashCode() : 0);
        result = 31 * result + bookPrice;
        result = 31 * result + (bookIsbn != null ? bookIsbn.hashCode() : 0);
        result = 31 * result + bookAmount;
        result = 31 * result + (bookImg != null ? bookImg.hashCode() : 0);
        result = 31 * result + (bookDetail != null ? bookDetail.hashCode() : 0);
        return result;
    }
}
