package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRole.PrimaryKeys> {
}
