package com.dkthanh.demo.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class ProjectFullInfoEntity implements Serializable {
    public static final String PROJECT_FULL_INFOR_MAP = "projectFullInforMap";

    // bacsic info
    protected Integer projectId;
    protected String projectName;
    protected Integer projectTeamId;
    protected Integer userId;
    protected String projectShortDes;
    protected Timestamp startDate;
    protected Timestamp endDate;
    protected Double goal;
    protected Double pledged;
    protected String investorCount;
    protected Integer projectStatusId;
    protected Integer recommended;
    // resources info
    protected Integer materialThumbnailId;
    protected String materialThumbnailName;
    protected String materialThumbnailPath;


    // category
    protected Integer categoryId;
    protected String categoryName;
    //
    protected Double percentPledged;

    // user interact
//    protected Integer likeCount;
    //
    protected Timestamp dayLeft;

    public ProjectFullInfoEntity() {
    }

    public ProjectFullInfoEntity(
            Integer projectId
            , String projectName
            , Integer projectTeamId
            , Integer userId
            , String projectShortDes
            , Timestamp startDate
            , Timestamp endDate
            , Double goal
            , Double pledged
            , String investorCount
            , Integer projectStatusId
            , Integer recommended
            , Timestamp dayLeft
            , Integer materialThumbnailId
            , String materialThumbnailName
            , String materialThumbnailPath
            , Integer categoryId
            , String categoryName
            , Double percentPledged) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectTeamId = projectTeamId;
        this.userId = userId;
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
    }
}
