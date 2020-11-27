package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findAll();

    Optional<UserEntity> findById(Integer id);

    Optional<UserEntity> findByUsername(String username);

    UserEntity save(UserEntity user);


}
