package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.PackageRepository;
import com.dkthanh.demo.domain.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    public Package savePackage(Package userChoosedPack){
        return packageRepository.save(userChoosedPack);
    }
}
