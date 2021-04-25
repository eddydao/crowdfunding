package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.MaterialTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialTypeRepository extends JpaRepository<MaterialTypeEntity, Integer> {
}
