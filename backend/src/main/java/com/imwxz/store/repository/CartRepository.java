package com.imwxz.store.repository;

import com.imwxz.store.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    public List<CartEntity> findAllByUserId(int userId);

    public CartEntity findByUserIdAndBookId(int userId, int bookId);
}
