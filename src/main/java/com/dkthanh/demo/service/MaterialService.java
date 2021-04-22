package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.MaterialRepository;
import com.dkthanh.demo.domain.MaterialEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public MaterialEntity getMaterialByProjectId(Integer id){
        Optional<List<MaterialEntity>> materials = materialRepository.findAllByProjectId(id);
        if(materials.isPresent() && materials.get().size() > 0){
            return materials.get().get(0);
        }
        return null;
    }

    public MaterialEntity saveImage(MaterialEntity entity){
        return materialRepository.save(entity);
    }
}
