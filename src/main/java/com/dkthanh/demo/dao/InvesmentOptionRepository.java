package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.InvesmentOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvesmentOptionRepository extends JpaRepository<InvesmentOptionEntity, Integer> {

    Optional<List<InvesmentOptionEntity>> findByProjectId(Integer projectId);

}
