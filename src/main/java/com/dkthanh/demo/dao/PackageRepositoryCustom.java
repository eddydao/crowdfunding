package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.PledgeReportEntity;

import java.util.List;

public interface PackageRepositoryCustom  {
    List<PledgeReportEntity> getPledgeByWeeks(Integer projectId);

    List<PledgeReportEntity> getOptionPercentage(Integer projectId);

    List<PledgeReportEntity> getPackageInfoByProjectId(Integer projectId);
}
