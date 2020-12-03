package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.dto.ProjectFullInfoEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectRepositoryCustom {
    Optional<List<ProjectFullInfoEntity>> getRecommendedProject();

    Optional<ProjectFullInfoEntity> getProjectDetail(Integer id);
}
