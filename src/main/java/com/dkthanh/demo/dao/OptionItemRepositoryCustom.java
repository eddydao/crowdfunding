package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.dto.ItemDtoEntity;

import java.util.List;
import java.util.Map;

public interface OptionItemRepositoryCustom {
    public List<ItemDtoEntity> findListItem(Map<String, Object> map);
}
