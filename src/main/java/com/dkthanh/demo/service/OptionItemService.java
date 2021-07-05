package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.OptionItemRepository;
import com.dkthanh.demo.domain.dto.ItemDtoEntity;
import com.dkthanh.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OptionItemService {
    @Autowired
    private OptionItemRepository repository;

    public List<ItemDtoEntity> getItemDtoListByProjectIdAndOptionId(Integer projectId, Integer optionId){

        Map<String, Object> map = new HashMap<>();

        map.put(Constant.PROJECT_KEY.PROJECT_ID, projectId);
        map.put(Constant.PROJECT_KEY.OPTION_ID, optionId);
        List<ItemDtoEntity> list = repository.findListItem(map);
        return list;
    }
}
