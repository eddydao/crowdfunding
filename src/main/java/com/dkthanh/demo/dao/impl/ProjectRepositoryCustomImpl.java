package com.dkthanh.demo.dao.impl;

import com.dkthanh.demo.dao.ProjectRepositoryCustom;
import com.dkthanh.demo.domain.dto.ProjectFullInfoEntity;

import java.util.List;
import java.util.Optional;

public class ProjectRepositoryCustomImpl implements ProjectRepositoryCustom {

    @Override
    public Optional<List<ProjectFullInfoEntity>> getPopularProject() {
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT "

        );
        return null;
    }

    @Override
    public Optional<ProjectFullInfoEntity> getProjectDetail(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<ProjectFullInfoEntity> getRecommendedProject() {
        return Optional.empty();
    }
}
