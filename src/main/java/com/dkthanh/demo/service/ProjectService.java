package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.ProjectRepository;
import com.dkthanh.demo.domain.CategoryEntity;
import com.dkthanh.demo.domain.InvesmentOptionEntity;
import com.dkthanh.demo.domain.MaterialEntity;
import com.dkthanh.demo.domain.ProjectEntity;
import com.dkthanh.demo.domain.dto.ProjectFullInfoEntity;
import com.dkthanh.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private InvesmentOptionService optionService;

    public List<ProjectFullInfoEntity> getPopularProjects(){
        Map<String, Object> map = new HashMap<>();
        map.put(Constant.PROJECT_KEY.PROJECT_STATUS, 3);
        List<ProjectFullInfoEntity> list = projectRepository.getProjectListWithDetail(map);
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    public ProjectFullInfoEntity getRecommendedProject(){
        Map<String, Object> map = new HashMap<>();
        map.put(Constant.PROJECT_KEY.IS_RECOMMENDED, 1);
        map.put(Constant.PROJECT_KEY.PROJECT_STATUS, 3);
        List<ProjectFullInfoEntity> project = projectRepository.getProjectListWithDetail(map);
        if(project instanceof ProjectFullInfoEntity && project.size() == 1){
            return project.get(0);
        }
        return null;
    }

    public ProjectFullInfoEntity getProjectDetail(Integer id){
        Map<String, Object> map = new HashMap<>();
        ProjectFullInfoEntity project = new ProjectFullInfoEntity();

        map.put(Constant.PROJECT_KEY.PROJECT_ID, id);
        List<ProjectFullInfoEntity> listProject = projectRepository.getProjectListWithDetail(map);
        if(listProject != null && !listProject.isEmpty()){
            project = listProject.get(0);
            Integer projectId = project.getProjectId();

            List<InvesmentOptionEntity> listOption = optionService.getListOptionOfProject(projectId);
            project.setListOption(listOption);
        }

        return project;
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
                        , entity.getTeamId()
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
