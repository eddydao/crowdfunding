package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.ProjectRepository;
import com.dkthanh.demo.domain.OptionEntity;
import com.dkthanh.demo.domain.ProjectEntity;
import com.dkthanh.demo.domain.dto.ProjectFullInfoEntity;
import com.dkthanh.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private InvesmentOptionService optionService;

    // get lastest project id
    public Integer getLastestProjectId(){
        Optional<ProjectEntity> entity = projectRepository.findFirstByOrderByProjectIdDesc();
        return entity.get().getProjectId();
    }
    // get popular project
    public List<ProjectFullInfoEntity> getPopularProjects(){
        Map<String, Object> map = new HashMap<>();
        List<Integer> status = new ArrayList<>();
        status.add(Constant.ProjectStatus.RUNNING.getId());
        map.put(Constant.PROJECT_KEY.PROJECT_STATUS, status);
        map.put(Constant.PROJECT_KEY.POPULAR, 1);
        List<ProjectFullInfoEntity> list = projectRepository.getProjectListWithDetail(map);
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    //get recommended project
    public ProjectFullInfoEntity getRecommendedProject(){
        Map<String, Object> map = new HashMap<>();
        map.put(Constant.PROJECT_KEY.IS_RECOMMENDED, 1);

        List<Integer> status = new ArrayList<>();
        status.add(Constant.ProjectStatus.RUNNING.getId());

        map.put(Constant.PROJECT_KEY.PROJECT_STATUS, status);
        List<ProjectFullInfoEntity> project = projectRepository.getProjectListWithDetail(map);
        if(project != null && project.size() == 1){
            return project.get(0);
        }
        return null;
    }

    // get project entity by id
    public ProjectEntity getProjectEntityById(Integer id){
        return projectRepository.findById(id).orElse(null);
    }

    // get project detail by id
    public ProjectFullInfoEntity getProjectDetail(Integer id){
        Map<String, Object> map = new HashMap<>();
        ProjectFullInfoEntity project = new ProjectFullInfoEntity();

        map.put(Constant.PROJECT_KEY.PROJECT_ID, id);
        List<ProjectFullInfoEntity> listProject = projectRepository.getProjectListWithDetail(map);
        if(listProject != null && !listProject.isEmpty()){
            project = listProject.get(0);
            Integer projectId = project.getProjectId();

            List<OptionEntity> listOption = optionService.getListOptionOfProject(projectId);
            project.setListOption(listOption);
        }

        return project;
    }

    // get project thumbnail path
//    public String getProjectThumbnailPath(int projectId){
//        return null;
//    }

    //search project by name
    public List<ProjectFullInfoEntity> searchProjectByNameContaining(String keyword){
        Map<String, Object> map = new HashMap<>();

        List<Integer> status = new ArrayList<>();
        status.add(Constant.ProjectStatus.RUNNING.getId());

        map.put(Constant.PROJECT_KEY.PROJECT_STATUS, status);
        keyword = "%" + keyword + "%";
        map.put(Constant.PROJECT_KEY.KEYWORD, keyword);

        List<ProjectFullInfoEntity> projects = projectRepository.getProjectListWithDetail(map);
        return projects;
    }

    // get project list of creator
    public List<ProjectFullInfoEntity> getProjectListOfCreator(Integer userId){
        Map<String, Object> map = new HashMap<>();
        map.put(Constant.PROJECT_KEY.USER_ID, userId);

        List<ProjectFullInfoEntity> list =  projectRepository.getProjectListWithDetail(map);
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }


    // get project list by category
    public List<ProjectFullInfoEntity> getProjectListByCategoryId(Integer categoryId){
        Map<String, Object> map = new HashMap<>();
        map.put(Constant.PROJECT_KEY.CATEGORY, categoryId);

        List<Integer> status = new ArrayList<>();
        status.add(Constant.ProjectStatus.RUNNING.getId());
        map.put(Constant.PROJECT_KEY.PROJECT_STATUS, status);

        List<ProjectFullInfoEntity> list =  projectRepository.getProjectListWithDetail(map);
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    // get project list by category
    public List<ProjectFullInfoEntity> filterProjectByConditions(Map<String, Object> map){
        List<ProjectFullInfoEntity> list =  projectRepository.getProjectListWithDetail(map);
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    public List<ProjectFullInfoEntity> getAllRunningProjectFullEntity(){
        Map<String, Object> map = new HashMap<>();

        List<Integer> status = new ArrayList<>();
        status.add(Constant.ProjectStatus.RUNNING.getId());

        map.put(Constant.PROJECT_KEY.PROJECT_STATUS, status);

        List<ProjectFullInfoEntity> list =  projectRepository.getProjectListWithDetail(map);
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    public List<ProjectFullInfoEntity> getAllProjectFullEntityNotWaitingOrEditing(){
        Map<String, Object> map = new HashMap<>();
        List<Integer> listStatus = new ArrayList<>();
        listStatus.add(Constant.ProjectStatus.APPROVED.getId());
        listStatus.add(Constant.ProjectStatus.RUNNING.getId());
        listStatus.add(Constant.ProjectStatus.STOP.getId());
        listStatus.add(Constant.ProjectStatus.COMPLETE.getId());
        listStatus.add(Constant.ProjectStatus.REJECT.getId());
        map.put(Constant.PROJECT_KEY.PROJECT_STATUS, listStatus);

        List<ProjectFullInfoEntity> list =  projectRepository.getProjectListWithDetail(map);
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    // save project entity
    public ProjectEntity saveProjectEntity(ProjectEntity entity){
        return projectRepository.save(entity);
    }

    public List<ProjectFullInfoEntity> getProjectListByStatus(Integer statusId) {
        Map<String, Object> map = new HashMap<>();
        List<Integer> status = new ArrayList<>();
        status.add(statusId);

        map.put(Constant.PROJECT_KEY.PROJECT_STATUS, status);

        List<ProjectFullInfoEntity> list =  projectRepository.getProjectListWithDetail(map);
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    public List<ProjectFullInfoEntity> getAllPendingApproveProject(){
        Map<String, Object> map = new HashMap<>();
        List<Integer> listStatus = new ArrayList<>();
        listStatus.add(Constant.ProjectStatus.WAITING.getId());
        map.put(Constant.PROJECT_KEY.PROJECT_STATUS, listStatus);

        List<ProjectFullInfoEntity> list =  projectRepository.getProjectListWithDetail(map);
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }



//    public int saveProjectFullInfoEntity(ProjectFullInfoEntity dto){
//        ProjectEntity entity = new ProjectEntity();
//        MaterialEntity materialEntity = new MaterialEntity();
//
//
//        if(dto != null){
//            entity.setProjectId(dto.getProjectId());
//            entity.setProjectName(dto.getProjectName());
////            entity.setCategoryId(dto.getCategoryId());
//            materialEntity.setProjectId(dto.getProjectId());
//            materialEntity.setMaterialTypeId(Constant.MaterialType.THUMBNAIL.getId());
//            materialEntity.setPath(dto.getMaterialThumbnailPath());
//            projectRepository.save(entity);
//            materialService.saveImage(materialEntity);
//        }
//
//
//        return 0;
//    }


}
