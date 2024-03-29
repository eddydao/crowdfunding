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

    @Autowired
    private OptionItemService optionItemService;

    public List<OptionEntity> getOptionListByProjectId(Integer projectId){
        return optionRepository.findOptionEntitiesByProjectProjectId(projectId);
    }

    public OptionEntity getOptionByProjectIdAndOptionId(Integer projectId, Integer optionId){
        return optionRepository.findOptionEntitiesByProjectProjectIdAndOptionId(projectId, optionId);
    }

    public OptionEntity save(OptionEntity optionEntity){
        return optionRepository.save(optionEntity);
    }

    public boolean removeOption(Integer projectId, Integer optionId){
        try{
            optionItemService.removeAllItemOfOption(optionId);

            optionRepository.deleteById(optionId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
