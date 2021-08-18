package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    void deleteById(Integer  categoryId);
}
