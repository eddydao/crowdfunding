package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.StoryRepository;
import com.dkthanh.demo.domain.StoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoryService {
    @Autowired
    private StoryRepository storyRepository;

    public StoryEntity getStoryByProjectId(Integer projectId){
        return storyRepository.findStoryEntityByProjectProjectId(projectId);
    }

    public StoryEntity save(StoryEntity storyEntity){
        return storyRepository.save(storyEntity);
    }
}
