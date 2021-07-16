package com.dkthanh.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "option_item", schema = "demo", catalog = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionItemEntity {
    @EmbeddedId
    private OptionItemEntityPK optionItemEntityPK;

    @ManyToOne
    @MapsId("optionId")
    private OptionEntity option;

    @ManyToOne
    @MapsId("itemId")
    private ItemEntity item;

    @Column(name = "quantity", nullable = true)
    private Integer quantity;

}
