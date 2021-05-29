package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.ItemRepository;
import com.dkthanh.demo.domain.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    public List<ItemEntity> getItemsOfProject(Integer projectId){
        return itemRepository.findItemEntitiesByProjectProjectId(projectId);
    }

}
