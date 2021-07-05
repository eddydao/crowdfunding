package com.dkthanh.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item", schema = "demo", catalog = "")
@Data
public class ItemEntity {
    @Id
    @Column(name = "item_id", nullable = false)
    private Integer itemId;
    @Column(name = "item_name", nullable = true, length = 255)
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @OneToMany(mappedBy = "option")
    private List<OptionItemEntity> items;


}
