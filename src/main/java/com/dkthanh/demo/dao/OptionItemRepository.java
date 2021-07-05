package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.OptionItemEntity;
import com.dkthanh.demo.domain.OptionItemEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionItemRepository extends JpaRepository<OptionItemEntity, OptionItemEntityPK> , OptionItemRepositoryCustom{
}
