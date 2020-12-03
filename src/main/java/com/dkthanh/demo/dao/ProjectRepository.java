package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>, ProjectRepositoryCustom {
}
