package com.imwxz.store.controller;

import com.imwxz.store.dao.CartDao;
import com.imwxz.store.entity.MessageEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @RequestMapping(value = "/api/cart", method = RequestMethod.GET)
    public MessageEntity getCart(@RequestParam("userId") Integer userId) {
        CartDao cart = new CartDao();
        MessageEntity ret = new MessageEntity();
        ret.setData(cart.getCartByUserId(userId));
        return ret;
    }

    @RequestMapping(value = "/api/cart", method = RequestMethod.PUT)
    public MessageEntity addCart(@RequestParam("userId") Integer userId,
                                 @RequestParam("bookId") Integer bookId,
                                 @RequestParam("cartAmount") Integer cartAmount) {
        CartDao cart = new CartDao();
        return ErrorHandler(cart.addCart(userId, bookId, cartAmount));
    }

    @RequestMapping(value = "/api/cart", method = RequestMethod.POST)
    public MessageEntity editCart(@RequestParam("userId") Integer userId,
                                  @RequestParam("bookId") Integer bookId,
                                  @RequestParam("cartAmount") Integer cartAmount) {
        CartDao cart = new CartDao();
        return ErrorHandler(cart.editCart(userId, bookId, cartAmount));
    }

    private MessageEntity ErrorHandler(int res) {
        MessageEntity ret = new MessageEntity();
        ret.setCode(res);
        if (res == -1)
            ret.setMsg("Unknown error!");
        else if (res == 1)
            ret.setMsg("No record exists!");
        return ret;
    }

    @RequestMapping(value = "/api/cart", method = RequestMethod.DELETE)
    public MessageEntity deleteCart(@RequestParam("userId") Integer userId,
                                    @RequestParam("bookId") Integer bookId) {
        CartDao cart = new CartDao();
        MessageEntity ret = new MessageEntity();
        return ErrorHandler(cart.deleteCart(userId, bookId));
    }
}