package com.imwxz.store.controller;

import com.imwxz.store.service.ICartService;
import com.imwxz.store.util.RetMessage;
import com.imwxz.store.util.jwt.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Autowired
    ICartService cart;

    @UserToken(adminFetch = false)
    @GetMapping(value = "/api/cart")
    public RetMessage getCart(@RequestParam("userId") Integer userId) {
        RetMessage ret = new RetMessage();
        ret.setData(cart.findCart(userId));
        return ret;
    }

    @UserToken(adminFetch = false)
    @PutMapping(value = "/api/cart")
    public RetMessage addCart(@RequestParam("userId") Integer userId,
                              @RequestParam("bookId") Integer bookId,
                              @RequestParam("cartAmount") Integer cartAmount) {
        return ErrorHandler(cart.addCart(userId, bookId, cartAmount));
    }

    @UserToken(adminFetch = false)
    @PostMapping(value = "/api/cart")
    public RetMessage editCart(@RequestParam("userId") Integer userId,
                               @RequestParam("bookId") Integer bookId,
                               @RequestParam("cartAmount") Integer cartAmount) {
        return ErrorHandler(cart.editCart(userId, bookId, cartAmount));
    }

    private RetMessage ErrorHandler(int res) {
        RetMessage ret = new RetMessage();
        ret.setCode(res);
        if (res == 1)
            ret.setMsg("No record exists!");
        return ret;
    }

    @UserToken(adminFetch = false)
    @DeleteMapping(value = "/api/cart")
    public RetMessage deleteCart(@RequestParam("userId") Integer userId,
                                 @RequestParam("bookId") Integer bookId) {
        return ErrorHandler(cart.deleteCart(userId, bookId));
    }
}