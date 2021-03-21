package com.dkthanh.demo.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;

@Data
@ToString
public class ProjectDto {
    protected Integer projectId;
    // basic info
    protected String projectName;
    protected String subTitle;
    protected Integer categoryId;
    protected Integer countryId;
    protected String imageName;
    protected MultipartFile fileDatas;
    protected OffsetDateTime startDate;
    protected OffsetDateTime endDate;
    protected Integer duration;

    public ProjectDto(){}

    public ProjectDto(
            Integer projectId
            , String projectName
            , String subTitle
            , Integer categoryId
            , Integer countryId
            , String imageName
            , MultipartFile fileDatas
            , OffsetDateTime startDate
            , Integer duration) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.subTitle = subTitle;
        this.categoryId = categoryId;
        this.countryId = countryId;
        this.imageName = imageName;
        this.fileDatas = fileDatas;
        this.startDate = startDate;
        this.duration = duration;
    }


}
