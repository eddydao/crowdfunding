package com.dkthanh.demo.domain.dto;

import lombok.Data;

@Data
public class FormInput {
    private String categoryId;
    private String statusId;
    private String searchInput;

    // project review section
    private String section;
    private String comment;
    private Integer reviewResult;
    private Integer isClose;
    private Integer projectId;
    private Integer optionId;
    private Integer itemId;

}
