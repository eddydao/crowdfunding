package com.dkthanh.demo.domain.dto;

import com.dkthanh.demo.domain.InvesmentOptionEntity;
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
    protected Integer teamId;
    protected String teamName;
    protected String projectShortDes;
    protected OffsetDateTime startDate;
    protected OffsetDateTime endDate;
    protected Double goal;
    protected Double pledged;
    protected Integer investorCount;
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
    // funding option of project
    protected List<InvesmentOptionEntity> listOption;
    protected Integer dayLeft;

    // key for measurement
    public Integer isEditable;

    public ProjectFullInfoEntity() {
    }

    public ProjectFullInfoEntity(
            Integer projectId
            , String projectName
            , Integer teamId
            , String teamName
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
            , Double percentPledged) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.teamId = teamId;
        this.teamName = teamName;
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

    public ProjectFullInfoEntity(
            Integer projectId
            , String projectName
            , Integer teamId
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
        this.teamId = teamId;
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
