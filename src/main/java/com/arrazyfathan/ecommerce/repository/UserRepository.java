package com.arrazyfathan.ecommerce.repository;


import com.arrazyfathan.ecommerce.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByPhoneNumber(String phoneNumber);
}
