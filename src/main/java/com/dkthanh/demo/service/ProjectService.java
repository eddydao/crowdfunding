package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.ProjectRepository;
import com.dkthanh.demo.domain.dto.ProjectFullInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectFullInfoEntity> getRecommendedProject(){
        Optional<List<ProjectFullInfoEntity>> list = projectRepository.getRecommendedProject();
        if(list.isPresent()){
            return list.get();
        }
        return null;
    }

    public ProjectFullInfoEntity getProjectDetail(Integer id){
        Optional<ProjectFullInfoEntity> projectDetail = projectRepository.getProjectDetail(id);
        return null;
    }

    public String getProjectThumbnailPath(int projectId){
        return null;
    }
}
