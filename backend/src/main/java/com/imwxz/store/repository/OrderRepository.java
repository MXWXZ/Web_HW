package com.imwxz.store.repository;

import com.imwxz.store.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query("select orderId,orderStatus,unix_timestamp(orderTime),orderPrice from OrderEntity o where userId = ?1")
    public List<Object[]> findUserIdOrder(int userId);

    @Query("select o.userByUserId.userName,orderId,orderStatus,unix_timestamp(orderTime),orderPrice from OrderEntity o")
    public List<Object[]> findAllOrder();
}
