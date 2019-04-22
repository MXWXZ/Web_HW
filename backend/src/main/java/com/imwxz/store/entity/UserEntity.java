package com.imwxz.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class UserEntity {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private int userStatus;
    private int userPermission;
    @JsonIgnore
    private Set<OrderEntity> ordersByUserId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(int userPermission) {
        this.userPermission = userPermission;
    }

    public Set<OrderEntity> getOrdersByUserId() {
        return ordersByUserId;
    }

    public void setOrdersByUserId(Set<OrderEntity> ordersByUserId) {
        this.ordersByUserId = ordersByUserId;
    }
}
