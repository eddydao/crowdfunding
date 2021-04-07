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
            , String userFullName
            , String projectShortDes
            , OffsetDateTime startDate
            , OffsetDateTime endDate
            , Double goal
            , Double pledged
            , Integer investorCount
            , Integer recommended
            , Integer countryId
            , Integer materialThumbnailId
            , String materialThumbnailName
            , String materialThumbnailPath
            , Integer statusId
            , String statusName
            , Integer categoryId
            , String categoryName
            , Double percentPledged
            , Integer dayLeft
            , Integer storyId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.userId = userId;
        this.userFullName = userFullName;
        this.projectShortDes = projectShortDes;
        this.startDate = startDate;
        this.endDate = endDate;
        this.goal = goal;
        this.pledged = pledged;
        this.investorCount = investorCount;
        this.recommended = recommended;
        this.countryId = countryId;
        this.materialThumbnailId = materialThumbnailId;
        this.materialThumbnailName = materialThumbnailName;
        this.materialThumbnailPath = materialThumbnailPath;
        this.statusId = statusId;
        this.statusName = statusName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.percentPledged = percentPledged;
        this.dayLeft = dayLeft;
        this.storyId = storyId;
    }

    // mapping basic info for jpa
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
        this.recommended = recommended;
        this.categoryId = categoryId;
    }
}
