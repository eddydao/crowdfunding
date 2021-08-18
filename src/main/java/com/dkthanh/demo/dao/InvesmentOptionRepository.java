package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvesmentOptionRepository extends JpaRepository<OptionEntity, Integer> {

//    Optional<List<OptionEntity>> findByProjectId(Integer projectId);

}
