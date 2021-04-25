package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {
}
