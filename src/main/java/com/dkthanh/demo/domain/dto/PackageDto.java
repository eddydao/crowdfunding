package com.dkthanh.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageDto {

    private Integer packageId;
    private Integer userId;
    private Integer projectId;
    private Integer optionId;
    private Long pledge;
    private Timestamp timestamp;

    public PackageDto(Integer userId, Integer projectId, Integer optionId, Long pledge, Timestamp timestamp) {
        this.userId = userId;
        this.projectId = projectId;
        this.optionId = optionId;
        this.pledge = pledge;
        this.timestamp = timestamp;
    }
}
