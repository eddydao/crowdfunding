package com.dkthanh.demo.domain.dto;

import com.dkthanh.demo.domain.ItemEntity;
import com.dkthanh.demo.domain.OptionItemEntity;
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
    private List<OptionItemEntity> optionItems;
    private List<ItemEntity> items;
    private List<ItemDtoEntity> itemDtoEntities;
    private Integer projectId;
    private Long pledge;
    private Integer newItemId;
    private Integer quantity;
    private Integer isTemp;

    public OptionDto(Integer optionId, String optionName, String optionDescription, Long fundMin, List<ItemDtoEntity> items, Integer projectId, Long pledge) {
        this.optionId = optionId;
        this.optionName = optionName;
        this.optionDescription = optionDescription;
        this.fundMin = fundMin;
        this.itemDtoEntities = items;
        this.projectId = projectId;
        this.pledge = pledge;
    }

    public OptionDto(Integer optionId, String optionName, String optionDescription, Long fundMin, List<ItemDtoEntity> items, Integer projectId, Long pledge, Integer isTemp) {
        this.optionId = optionId;
        this.optionName = optionName;
        this.optionDescription = optionDescription;
        this.fundMin = fundMin;
        this.itemDtoEntities = items;
        this.projectId = projectId;
        this.pledge = pledge;
        this.isTemp = isTemp;
    }
}
