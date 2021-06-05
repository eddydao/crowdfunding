package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.PackageRepository;
import com.dkthanh.demo.domain.Package;
import com.dkthanh.demo.domain.PledgeReportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    public Package savePackage(Package userChoosedPack){
        return packageRepository.save(userChoosedPack);
    }

    public List<Package> getAllPackageByProjectId(Integer projectId){
        return packageRepository.findAllByProjectProjectId(projectId);
    }

    public List<PledgeReportEntity> getPackageInfoByProjectId(Integer projectId){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<PledgeReportEntity> list = packageRepository.getPackageInfoByProjectId(projectId);
        for(int i = 0; i< list.size(); i++){
            list.get(i).setTimestamp(list.get(i).getTimestampOffset().format(formatter));
        }
        return list;
    }
}
