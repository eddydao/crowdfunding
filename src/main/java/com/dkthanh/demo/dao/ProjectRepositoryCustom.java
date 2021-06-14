package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.dto.ProjectFullInfoEntity;

import java.util.List;
import java.util.Map;

public interface ProjectRepositoryCustom {
    List<ProjectFullInfoEntity> getProjectListWithDetail(Map<String, Object> map);

}
