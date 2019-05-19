package com.imwxz.store.repository;

import com.imwxz.store.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUserName(String userName);

    UserEntity findByUserEmail(String userEmail);
}
