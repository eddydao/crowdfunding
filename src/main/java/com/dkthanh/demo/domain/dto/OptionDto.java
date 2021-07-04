package com.dkthanh.demo.domain.dto;

import com.dkthanh.demo.domain.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OptionDto {
    private Integer optionId;
    private String optionName;
    private String optionDescription;
    private Long fundMin;
    private List<ItemEntity> items;
    private Integer projectId;
    private Long pledge;
    private Integer newItemId;

    public OptionDto(Integer optionId, String optionName, String optionDescription, Long fundMin, List<ItemEntity> items, Integer projectId, Long pledge) {
        this.optionId = optionId;
        this.optionName = optionName;
        this.optionDescription = optionDescription;
        this.fundMin = fundMin;
        this.items = items;
        this.projectId = projectId;
        this.pledge = pledge;
    }
}
