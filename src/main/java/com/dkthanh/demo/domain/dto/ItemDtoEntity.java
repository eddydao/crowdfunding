package com.dkthanh.demo.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemDtoEntity implements Serializable {
    public static final String ITEM_DTO_ENTITY_MAP = "itemDtoEntityMap";

    private Integer itemId;
    private String itemName;
    private Integer quantity;
    private Integer projectId;
    private Integer optionId;

    public ItemDtoEntity(){}

    // constructor for entity map
    public ItemDtoEntity(
            Integer itemId,
            Integer projectId,
            Integer optionId,
            String itemName,
            Integer quantity
    ){
        this.itemId = itemId;
        this.projectId = projectId;
        this.optionId = optionId;
        this.itemName = itemName;
        this.quantity = quantity;
    }


}
