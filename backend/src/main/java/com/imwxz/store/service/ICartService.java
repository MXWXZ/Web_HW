package com.imwxz.store.service;

import com.imwxz.store.entity.CartEntity;

import java.util.List;

public interface ICartService {
    public List findCart(int userId);

    public CartEntity findCartEntity(int userId, int bookId);

    public int addCart(int userId, int bookId, int cartAmount);

    public int editCart(int userId, int bookId, int cartAmount);

    public int deleteCart(int userId, int bookId);
}
