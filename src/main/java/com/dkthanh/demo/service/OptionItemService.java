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

    public ItemDtoEntity getItemDtoByOptionIdAndItemId(Integer optionId , Integer itemId){
        Map<String, Object> map = new HashMap<>();

        map.put(Constant.PROJECT_KEY.ITEM_ID, itemId);
        map.put(Constant.PROJECT_KEY.OPTION_ID, optionId);
        List<ItemDtoEntity> list = repository.findListItem(map);
        return (list != null && !list.isEmpty()) ? list.get(0) : null;
    }

    public OptionItemEntity save(OptionItemEntity optionItemEntity){
        return null;
    }


    public boolean saveOptionItem(Map<String, Object> map){
        if(map.get(Constant.PROJECT_KEY.OPTION_ID) != null && map.get(Constant.PROJECT_KEY.ITEM_ID) != null){
            if(this.getItemDtoByOptionIdAndItemId( (Integer)map.get(Constant.PROJECT_KEY.OPTION_ID), (Integer)map.get(Constant.PROJECT_KEY.ITEM_ID)) != null ){
                if((Integer) map.get(Constant.PROJECT_KEY.QUANTITY) == 0 ){
                    try{
                        this.removeOptionItemById((int)map.get(Constant.PROJECT_KEY.OPTION_ID), (int)map.get(Constant.PROJECT_KEY.ITEM_ID));
                    }catch (Exception e){
                        e.printStackTrace();
                        return false;
                    }
                    return true;
                }

                try{
                    repository.updateOptionItem(map);
                }catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }

        try{
            repository.insertOptionItem(map);
        }catch (Exception e){
            e.printStackTrace();
            return false;
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
