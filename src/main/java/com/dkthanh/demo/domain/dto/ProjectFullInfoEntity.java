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
    protected Long goal;
    protected Long pledged;
    protected Integer investorCount;
    protected Integer recommended;
    protected Integer countryId;
    protected String countryName;
    protected String thumbnailPath;
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
            , Long goal
            , Long pledged
            , Integer investorCount
            , Integer recommended
            , String thumbnailPath
            , Integer countryId
            , String countryName
            , Integer statusId
            , String statusName
            , Integer categoryId
            , String categoryName
            , Double percentPledged
            , Integer dayLeft) {
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
        this.thumbnailPath = thumbnailPath;
        this.countryId = countryId;
        this.countryName = countryName;
        this.statusId = statusId;
        this.statusName = statusName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.percentPledged = percentPledged;
        this.dayLeft = dayLeft;
    }

    // mapping basic info for jpa
    public ProjectFullInfoEntity(
            Integer projectId
            , String projectName
            , Integer userId
            , String projectShortDes
            , OffsetDateTime startDate
            , OffsetDateTime endDate
            , Long goal
            , Long pledged
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
