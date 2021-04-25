package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<StoryEntity, Integer> {
}
