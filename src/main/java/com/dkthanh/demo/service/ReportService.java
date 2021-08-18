package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.PackageRepository;
import com.dkthanh.demo.domain.PledgeReportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private PackageRepository packageRepository;


    public List<PledgeReportEntity> getPLedgeAmountByWeeks(Integer projectId){
        return packageRepository.getPledgeByWeeks(projectId);
    }

    public List<PledgeReportEntity> getOptionPercentageByProjectId(Integer projectId){
        return packageRepository.getOptionPercentage(projectId);
    }
}
