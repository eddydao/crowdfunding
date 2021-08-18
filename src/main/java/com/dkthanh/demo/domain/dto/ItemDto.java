package com.dkthanh.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Integer itemId;
    private String itemName;
    private Integer projectId;
}
