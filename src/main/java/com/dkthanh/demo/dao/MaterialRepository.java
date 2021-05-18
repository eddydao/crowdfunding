package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.MaterialEntity;
import com.dkthanh.demo.domain.MaterialEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<MaterialEntity, MaterialEntityPK> {
//    Optional<List<MaterialEntity>> findAllByProjectId(Integer id);
    Optional<List<MaterialEntity>> findAllByProjectProjectIdAndMaterialTypeId(int projectId, int materialTypeId);
}
