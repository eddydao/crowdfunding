package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.InvesmentOptionRepository;
import com.dkthanh.demo.domain.InvesmentOptionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvesmentOptionService {
    @Autowired
    private InvesmentOptionRepository repository;
    public List<InvesmentOptionEntity> getListOptionOfProject(Integer projectId){
        Optional<List<InvesmentOptionEntity>> listOptional = repository.findByProjectId(projectId);
        if(listOptional.isPresent()){
            return listOptional.get();
        }
        return null;
    }
}
