package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();

    Optional<User> findById(Integer id);

    Optional<User> findByUserName(String userName);

    User save(User user);


}
