package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.MaterialTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialTypeRepository extends JpaRepository<MaterialTypeEntity, Integer> {
}
