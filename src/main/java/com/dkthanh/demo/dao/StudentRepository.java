package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
}
