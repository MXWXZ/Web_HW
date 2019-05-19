package com.imwxz.store.service.impl;

import com.imwxz.store.entity.BookEntity;
import com.imwxz.store.entity.CartEntity;
import com.imwxz.store.entity.OrderEntity;
import com.imwxz.store.entity.OrderItemEntity;
import com.imwxz.store.repository.BookRepository;
import com.imwxz.store.repository.CartRepository;
import com.imwxz.store.repository.OrderItemRepository;
import com.imwxz.store.repository.OrderRepository;
import com.imwxz.store.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderRepository order;

    @Autowired
    private CartRepository cart;

    @Autowired
    private OrderItemRepository orderItem;

    @Autowired
    private BookRepository book;

    @Override
    public List findOrderByUserId(int userId) {
        List<Object[]> res = order.findUserIdOrder(userId);
        List<Object> ret = new ArrayList<>();
        for (Object[] i : res) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", i[0]);
            map.put("orderStatus", i[1]);
            map.put("orderTime", i[2]);
            map.put("orderPrice", i[3]);
            ret.add(map);
        }
        return ret;
    }

    @Override
    public List findOrderByOrderId(int orderId) {
        List<OrderItemEntity> res = orderItem.findAllByOrderId(orderId);
        List<Object> ret = new ArrayList<>();
        for (OrderItemEntity i : res) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderItemId", i.getOrderItemId());
            map.put("bookId", i.getBookId());
            map.put("bookName", i.getBookByBookId().getBookName());
            map.put("bookPrice", i.getBookByBookId().getBookPrice());
            map.put("orderAmount", i.getOrderAmount());
            ret.add(map);
        }
        return ret;
    }

    @Transactional
    @Override
    public int addOrder(int userId, List<Integer> cartList) {
        if (cartList.size() == 0)
            return 4;
        List<CartEntity> res = cart.findAllByUserId(userId);
        if (res.size() == 0)
            return 1;

        OrderEntity obj = new OrderEntity();
        obj.setUserId(userId);
        obj.setOrderStatus(1);
        order.save(obj);

        int tot = 0;
        for (Integer i : cartList) {
            boolean flag = false;
            for (CartEntity j : res) {
                if (j.getCartId() == i) {
                    OrderItemEntity item = new OrderItemEntity();
                    item.setOrderId(obj.getOrderId());
                    item.setBookId(j.getBookId());
                    item.setOrderAmount(j.getCartAmount());
                    orderItem.save(item);

                    BookEntity bookObj = j.getBookByBookId();
                    if (bookObj.getBookAmount() < j.getCartAmount()) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return 2;
                    }
                    bookObj.setBookAmount(bookObj.getBookAmount() - j.getCartAmount());
                    book.save(bookObj);
                    cart.delete(j);

                    tot += bookObj.getBookPrice() * j.getCartAmount();

                    flag = true;
                    break;
                }
            }
            if (!flag) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return 3;
            }
        }
        order.flush();
        obj.setOrderPrice(tot);
        order.save(obj);
        return 0;
    }
}