package com.imwxz.store.service;

import java.util.List;

public interface IOrderService {
    public List findOrderByUserId(int userId);

    public List findOrderByOrderId(int orderId);

    public int addOrder(int userId, List<Integer> cartList);
}
