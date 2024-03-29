package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    List<ItemEntity> findItemEntitiesByProjectProjectId(Integer projectId);
}
