package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.OptionItemRepository;
import com.dkthanh.demo.domain.OptionItemEntity;
import com.dkthanh.demo.domain.dto.ItemDtoEntity;
import com.dkthanh.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
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

    public OptionItemEntity save(OptionItemEntity optionItemEntity){
        return null;
    }


    public boolean saveOptionItem(Map<String, Object> map){
        if(map.get(Constant.PROJECT_KEY.OPTION_ID) != null && map.get(Constant.PROJECT_KEY.ITEM_ID) != null){
            try{
                repository.updateOptionItem(map);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        } else{
            try{
                repository.insertOptionItem(map);
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean removeOptionItemById(Integer optionId, Integer itemId){
        Map<String, Object> map = new HashMap<>();

        map.put(Constant.PROJECT_KEY.OPTION_ID, optionId);
        map.put(Constant.PROJECT_KEY.ITEM_ID, itemId);
        try{
            repository.deleteByOptionIdAndItemId(map);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
