package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.OptionItemEntity;
import com.dkthanh.demo.domain.OptionItemEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionItemRepository extends JpaRepository<OptionItemEntity, OptionItemEntityPK> , OptionItemRepositoryCustom{

}
