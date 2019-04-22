package com.imwxz.store.controller;

import com.imwxz.store.dao.OrderDao;
import com.imwxz.store.entity.MessageEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    public MessageEntity getOrders() {
        OrderDao order = new OrderDao();
        MessageEntity ret = new MessageEntity();
        ret.setData(order.getAllOrderWithUsername());
        return ret;
    }
}
