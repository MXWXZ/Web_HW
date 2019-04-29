package com.imwxz.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartEntity {
    private int cartId;
    private int userId;
    private int bookId;
    private int cartAmount;
    @JsonIgnore
    private UserEntity userByUserId;
    @JsonIgnore
    private BookEntity bookByBookId;

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public BookEntity getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(BookEntity bookByBookId) {
        this.bookByBookId = bookByBookId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(int cartAmount) {
        this.cartAmount = cartAmount;
    }
}
