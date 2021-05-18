package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.MaterialRepository;
import com.dkthanh.demo.domain.MaterialEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<MaterialEntity> getMaterialByProjectIdAndType(int projectId, int materialTypeId){
        return materialRepository.findAllByProjectProjectIdAndMaterialTypeId(projectId, materialTypeId).orElse(null);
    }

    public MaterialEntity saveImage(MaterialEntity entity){
        return materialRepository.save(entity);
    }
}
