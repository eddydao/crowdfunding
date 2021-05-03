package com.dkthanh.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "item", schema = "demo", catalog = "")
@Data
public class ItemEntity {
    @Id
    @Column(name = "item_id", nullable = false)
    private Integer itemId;
    @Column(name = "item_name", nullable = true, length = 255)
    private String itemName;
    @Column(name = "quantity", nullable = true)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private OptionEntity option;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;


}
