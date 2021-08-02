package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.dto.ItemDtoEntity;

import java.util.List;
import java.util.Map;

public interface OptionItemRepositoryCustom {
    List<ItemDtoEntity> findListItem(Map<String, Object> map);

//    void removeOptionItemById(Map<String, Object> map);
    int insertOptionItem(Map<String, Object> map);
    int updateOptionItem(Map<String, Object> map);

    int deleteByOptionIdAndItemId(Map<String, Object> map);
    int deleteByOptionId(Map<String, Object> map);
}
