package com.imwxz.store.repository;

import com.imwxz.store.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query("select orderId,orderStatus,unix_timestamp(orderTime),orderPrice from OrderEntity o where userId = ?1")
    public List<Object[]> findUserIdOrder(int userId);

    @Query("select count(*),sum((select sum(orderAmount) from OrderItemEntity as oi where oi.orderId=o.orderId))," +
            "sum(orderPrice),unix_timestamp(min(orderTime)),unix_timestamp(max(orderTime)) from OrderEntity o where userId = ?1")
    public List<Object[]> getStat(int userId);

    @Query("select count(*),sum((select sum(orderAmount) from OrderItemEntity as oi where oi.orderId=o.orderId))," +
            "sum(orderPrice),unix_timestamp(min(orderTime)),unix_timestamp(max(orderTime)) from OrderEntity o where userId = ?1 " +
            "and orderTime between FROM_UNIXTIME(?2) and FROM_UNIXTIME(?3)")
    public List<Object[]> getStat(int userId, int minTime, int maxTime);
}
