package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>, ProjectRepositoryCustom {
    Optional<List<ProjectEntity>> findProjectEntitiesByProjectNameContaining(String keyword);

    Optional<ProjectEntity> findFirstByOrderByProjectIdDesc();

}
