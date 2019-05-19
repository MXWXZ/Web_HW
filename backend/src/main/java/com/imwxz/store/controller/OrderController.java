package com.imwxz.store.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imwxz.store.service.IOrderService;
import com.imwxz.store.util.RetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private IOrderService order;

    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    public RetMessage getOrders(@RequestParam(value = "userId", required = false) Integer userId,
                                @RequestParam(value = "orderId", required = false) Integer orderId) {
        RetMessage ret = new RetMessage();
        if (userId != null)
            ret.setData(order.findOrderByUserId(userId));
        else if (orderId != null)
            ret.setData(order.findOrderByOrderId(orderId));
        return ret;
    }

    @RequestMapping(value = "/api/orders", method = RequestMethod.PUT)
    public RetMessage addOrders(@RequestBody String json) {
        RetMessage ret = new RetMessage();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            int userId = rootNode.path("userId").asInt();
            List<Integer> cartList = new ArrayList<>();
            JsonNode node = rootNode.path("cartId");
            for (JsonNode i : node)
                cartList.add(i.asInt());

            int res = order.addOrder(userId, cartList);
            ret.setCode(res);
            if (res == 1)
                ret.setMsg("Empty cart!");
            else if (res == 2)
                ret.setMsg("At least one book's amount is not enough!");
            else if (res == 3)
                ret.setMsg("At least one book is not in cart!");
            else if (res == 4)
                ret.setMsg("Empty order!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
