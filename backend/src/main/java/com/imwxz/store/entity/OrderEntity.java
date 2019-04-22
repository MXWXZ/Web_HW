package com.imwxz.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class OrderEntity {
    private int orderId;
    private int orderStatus;
    private int userId;
    @JsonIgnore
    private UserEntity userByUserId;
    @JsonIgnore
    private Set<OrderItemEntity> orderItemsByOrderId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Set<OrderItemEntity> getOrderItemsByOrderId() {
        return orderItemsByOrderId;
    }

    public void setOrderItemsByOrderId(Set<OrderItemEntity> orderItemsByOrderId) {
        this.orderItemsByOrderId = orderItemsByOrderId;
    }
}
