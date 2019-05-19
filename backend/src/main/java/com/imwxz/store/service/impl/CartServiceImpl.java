package com.imwxz.store.service.impl;

import com.imwxz.store.entity.BookEntity;
import com.imwxz.store.entity.CartEntity;
import com.imwxz.store.repository.CartRepository;
import com.imwxz.store.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartRepository cart;

    @Override
    public List findCart(int userId) {
        List<CartEntity> obj = cart.findAllByUserId(userId);
        List<Object> ret = new ArrayList<>();
        for (CartEntity i : obj) {
            BookEntity book = i.getBookByBookId();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cartId", i.getCartId());
            map.put("bookId", i.getBookId());
            map.put("bookName", book.getBookName());
            map.put("bookImg", book.getBookImg());
            map.put("bookAmount", book.getBookAmount());
            map.put("bookPrice", book.getBookPrice());
            map.put("cartAmount", i.getCartAmount());
            ret.add(map);
        }
        return ret;
    }

    @Override
    public CartEntity findCartEntity(int userId, int bookId) {
        return cart.findByUserIdAndBookId(userId, bookId);
    }

    @Transactional
    @Override
    public int addCart(int userId, int bookId, int cartAmount) {
        CartEntity former = findCartEntity(userId, bookId);
        if (former == null) {
            CartEntity obj = new CartEntity();
            obj.setBookId(bookId);
            obj.setUserId(userId);
            obj.setCartAmount(cartAmount);
            cart.save(obj);
        } else {
            former.setCartAmount(former.getCartAmount() + cartAmount);
            cart.save(former);
        }
        return 0;
    }

    @Transactional
    @Override
    public int editCart(int userId, int bookId, int cartAmount) {
        CartEntity former = findCartEntity(userId, bookId);
        if (former == null)
            return 1;

        former.setCartAmount(cartAmount);
        cart.save(former);
        return 0;
    }

    @Transactional
    @Override
    public int deleteCart(int userId, int bookId) {
        CartEntity former = findCartEntity(userId, bookId);
        if (former == null)
            return 1;

        cart.delete(former);
        return 0;
    }
}
