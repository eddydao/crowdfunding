package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity, Integer> {
    List<OptionEntity> findOptionEntitiesByProjectProjectId(Integer projectId);
}
