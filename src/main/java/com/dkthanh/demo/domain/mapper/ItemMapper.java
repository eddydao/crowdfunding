package com.dkthanh.demo.domain.mapper;

import com.dkthanh.demo.domain.dto.ItemDtoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@SqlResultSetMapping(
    name = ItemDtoEntity.ITEM_DTO_ENTITY_MAP,
    classes = {
        @ConstructorResult(
            targetClass = ItemDtoEntity.class,
            columns = {
                @ColumnResult(name = "item_id", type = Integer.class),
                @ColumnResult(name = "project_id", type = Integer.class),
                @ColumnResult(name = "option_id", type = Integer.class),
                @ColumnResult(name = "item_name", type = String.class),
                @ColumnResult(name = "quantity", type = Integer.class)
            })
    }
)
@Entity
public class ItemMapper {
    @Id
    Integer id;
}
