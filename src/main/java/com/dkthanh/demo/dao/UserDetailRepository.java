package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Integer> {
}
