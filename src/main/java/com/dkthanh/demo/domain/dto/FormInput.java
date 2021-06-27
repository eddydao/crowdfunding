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
    private String qualifiedResult;
    private Integer isClose;
    private Integer projectId;

}
