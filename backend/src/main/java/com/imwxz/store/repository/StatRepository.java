package com.imwxz.store.repository;

import com.imwxz.store.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatRepository extends JpaRepository<OrderEntity, Integer> {
    @Query("select count(*),sum((select sum(orderAmount) from OrderItemEntity as oi where oi.orderId=o.orderId))," +
            "sum(orderPrice),unix_timestamp(min(orderTime)),unix_timestamp(max(orderTime)) from OrderEntity o where userId = ?1")
    public List<Object[]> getStat(int userId);

    @Query("select count(*),sum((select sum(orderAmount) from OrderItemEntity as oi where oi.orderId=o.orderId))," +
            "sum(orderPrice),unix_timestamp(min(orderTime)),unix_timestamp(max(orderTime)) from OrderEntity o where userId = ?1 " +
            "and orderTime between FROM_UNIXTIME(?2) and FROM_UNIXTIME(?3)")
    public List<Object[]> getStat(int userId, int minTime, int maxTime);

    @Query("select unix_timestamp(min(orderTime)),unix_timestamp(max(orderTime)) from OrderEntity o")
    public List<Object[]> getTotalStat();

    @Query(value = "select b.book_name,sum(oi.order_amount),b.book_price " +
            "from `order` o,`order_item` oi,`book` b " +
            "where oi.order_id=o.order_id and oi.book_id=b.book_id and o.order_time between FROM_UNIXTIME(?1) and FROM_UNIXTIME(?2)" +
            "group by oi.book_id", nativeQuery = true)
    public List<Object[]> getBookStat(int minTime, int maxTime);


    @Query("select o.userId,o.userByUserId.userName,sum(select sum(orderAmount) from OrderItemEntity oi where oi.orderId=o.orderId),sum(o.orderPrice) " +
            "from OrderEntity o " +
            "where orderTime between FROM_UNIXTIME(?1) and FROM_UNIXTIME(?2) " +
            "group by o.userId")
    public List<Object[]> getUserStat(int minTime, int maxTime);
}
