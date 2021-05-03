package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.OptionRepository;
import com.dkthanh.demo.domain.OptionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OptionService {
    @Autowired
    private OptionRepository optionRepository;

    public List<OptionEntity> getOptionListByProjectId(Integer projectId){
        return optionRepository.findOptionEntitiesByProjectProjectId(projectId);
    }
}
