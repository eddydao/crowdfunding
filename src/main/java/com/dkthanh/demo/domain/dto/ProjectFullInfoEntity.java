package com.dkthanh.demo.domain.dto;

import com.dkthanh.demo.domain.OptionEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class ProjectFullInfoEntity implements Serializable {
    public static final String PROJECT_FULL_INFOR_MAP = "projectFullInforMap";

    // bacsic info
    protected Integer projectId;
    protected String projectName;
    protected Integer userId;
    protected String userFullName;
    protected String projectShortDes;
    protected OffsetDateTime startDate;
    protected OffsetDateTime endDate;
    protected Double goal;
    protected Double pledged;
    protected Integer investorCount;
    protected Integer projectStatusId;
    protected Integer recommended;
    protected Integer countryId;
    // resources info
    protected Integer materialThumbnailId;
    protected String materialThumbnailName;
    protected String materialThumbnailPath;
    // status
    protected Integer statusId;
    protected String statusName;

    // category
    protected Integer categoryId;
    protected String categoryName;
    //
    protected Double percentPledged;
    // funding option of project
    protected List<OptionEntity> listOption;
    protected Integer dayLeft;

    // key for measurement
    public Integer isEditable;

    // story
    protected Integer storyId;

    public ProjectFullInfoEntity() {
    }


    public ProjectFullInfoEntity(
            Integer projectId
            , String projectName
            , Integer userId
            , String userName
            , String projectShortDes
            , OffsetDateTime startDate
            , OffsetDateTime endDate
            , Double goal
            , Double pledged
            , Integer investorCount
            , Integer projectStatusId
            , Integer recommended
            , Integer dayLeft
            , Integer materialThumbnailId
            , String materialThumbnailName
            , String materialThumbnailPath
            , Integer categoryId
            , String categoryName
            , Double percentPledged
            , Integer statusId
            , String statusName
            , Integer storyId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.userId = userId;
        this.userFullName = userName;
        this.projectShortDes = projectShortDes;
        this.startDate = startDate;
        this.endDate = endDate;
        this.goal = goal;
        this.pledged = pledged;
        this.investorCount = investorCount;
        this.projectStatusId = projectStatusId;
        this.recommended = recommended;
        this.dayLeft = dayLeft;
        this.materialThumbnailId = materialThumbnailId;
        this.materialThumbnailName = materialThumbnailName;
        this.materialThumbnailPath = materialThumbnailPath;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.percentPledged = percentPledged;
        this.statusId = statusId;
        this.statusName = statusName;
        this.storyId = storyId;
    }

    // mapping basic info for JPA
    public ProjectFullInfoEntity(
            Integer projectId
            , String projectName
            , Integer userId
            , String projectShortDes
            , OffsetDateTime startDate
            , OffsetDateTime endDate
            , Double goal
            , Double pledged
            , Integer investorCount
            , Integer projectStatusId
            , Integer recommended
            , Integer categoryId
    )
    {
        this.projectId = projectId;
        this.projectName = projectName;
        this.userId = userId;
        this.projectShortDes = projectShortDes;
        this.startDate = startDate;
        this.endDate = endDate;
        this.goal = goal;
        this.pledged = pledged;
        this.investorCount = investorCount;
        this.projectStatusId = projectStatusId;
        this.recommended = recommended;
        this.categoryId = categoryId;
    }
}
