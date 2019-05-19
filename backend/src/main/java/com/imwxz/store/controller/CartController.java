package com.imwxz.store.controller;

import com.imwxz.store.service.ICartService;
import com.imwxz.store.util.RetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    ICartService cart;

    @RequestMapping(value = "/api/cart", method = RequestMethod.GET)
    public RetMessage getCart(@RequestParam("userId") Integer userId) {
        RetMessage ret = new RetMessage();
        ret.setData(cart.findCart(userId));
        return ret;
    }

    @RequestMapping(value = "/api/cart", method = RequestMethod.PUT)
    public RetMessage addCart(@RequestParam("userId") Integer userId,
                              @RequestParam("bookId") Integer bookId,
                              @RequestParam("cartAmount") Integer cartAmount) {
        return ErrorHandler(cart.addCart(userId, bookId, cartAmount));
    }

    @RequestMapping(value = "/api/cart", method = RequestMethod.POST)
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

    @RequestMapping(value = "/api/cart", method = RequestMethod.DELETE)
    public RetMessage deleteCart(@RequestParam("userId") Integer userId,
                                 @RequestParam("bookId") Integer bookId) {
        return ErrorHandler(cart.deleteCart(userId, bookId));
    }
}