package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.InvesmentOptionRepository;
import com.dkthanh.demo.domain.OptionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InvesmentOptionService {
    @Autowired
    private InvesmentOptionRepository repository;
    public List<OptionEntity> getListOptionOfProject(Integer projectId){
        return null;
    }
}
