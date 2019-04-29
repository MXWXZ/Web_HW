package com.imwxz.store.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imwxz.store.dao.OrderDao;
import com.imwxz.store.entity.MessageEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    @RequestMapping(value = "/api/orders", method = RequestMethod.GET)
    public MessageEntity getOrders(@RequestParam(value = "userId", required = false) Integer userId,
                                   @RequestParam(value = "orderId", required = false) Integer orderId) {
        OrderDao order = new OrderDao();
        MessageEntity ret = new MessageEntity();
        if (userId != null)
            ret.setData(order.getOrderByUserId(userId));
        else if (orderId != null)
            ret.setData(order.getOrderItemByOrderId(orderId));
        else
            ret.setData(order.getAllOrderWithUsername());
        return ret;
    }

    @RequestMapping(value = "/api/orders", method = RequestMethod.PUT)
    public MessageEntity addOrders(@RequestBody String json) {
        OrderDao order = new OrderDao();
        MessageEntity ret = new MessageEntity();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            int userId = rootNode.path("userId").asInt();
            List<Integer> cartId = new ArrayList<Integer>();
            JsonNode node = rootNode.path("cartId");
            for (JsonNode i : node)
                cartId.add(i.asInt());

            int res = order.addOrder(userId, cartId);
            ret.setCode(res);
            if (res == 1)
                ret.setMsg("Empty cart!");
            else if (res == -1)
                ret.setMsg("Unknown error!");
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
