package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.ProjectRepository;
import com.dkthanh.demo.domain.CategoryEntity;
import com.dkthanh.demo.domain.MaterialEntity;
import com.dkthanh.demo.domain.ProjectEntity;
import com.dkthanh.demo.domain.dto.ProjectFullInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MaterialService materialService;

    public List<ProjectFullInfoEntity> getPopularProjects(){
        Optional<List<ProjectFullInfoEntity>> list = projectRepository.getPopularProject();
        if(list.isPresent()){
            return list.get();
        }
        return null;
    }

    public ProjectFullInfoEntity getRecommendedProject(){
        List<ProjectFullInfoEntity> project = projectRepository.getRecommendedProject();
        if(project instanceof ProjectFullInfoEntity && project.size() == 1){
            return project.get(0);
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

    public List<ProjectFullInfoEntity> searchProjectByNameContaining(String keyword){
        Optional<List<ProjectEntity>> listOptional = projectRepository.findProjectEntitiesByProjectNameContaining(keyword);
        if(listOptional.isPresent()){
            List<ProjectEntity> listEntity = listOptional.get();
            List<ProjectFullInfoEntity> listFullInfo = new ArrayList<>();
            int rowCount = listEntity.size();
            for(int i = 0; i < rowCount; i++){
                ProjectEntity entity = listEntity.get(i);
                ProjectFullInfoEntity fullEntity = new ProjectFullInfoEntity(
                        entity.getProjectId()
                        , entity.getProjectName()
                        , entity.getProjectTeamId()
                        , entity.getUserId()
                        , entity.getProjectShortDes()
                        , entity.getStartDate()
                        , entity.getEndDate()
                        , entity.getGoal()
                        , entity.getPledged()
                        , entity.getInvestorCount()
                        , entity.getProjectStatusId()
                        , entity.getRecommended()
                        , entity.getCategoryId()
                );
                CategoryEntity categoryEntity = categoryService.getCategoryById(entity.getCategoryId());
                if(!categoryEntity.equals(null)){
                    fullEntity.setCategoryName(categoryEntity.getCategoryName());
                }

                MaterialEntity materialEntity = materialService.getMaterialByProjectId(entity.getProjectId());
                fullEntity.setMaterialThumbnailId(materialEntity.getMaterialId());
                fullEntity.setMaterialThumbnailName(materialEntity.getDescription());
                fullEntity.setMaterialThumbnailPath(materialEntity.getPath());

                listFullInfo.add(fullEntity);
            }
            return listFullInfo;
        }
        return null;
    }
}
