package com.dkthanh.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item", schema = "demo", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    @Id
    @Column(name = "item_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    @Column(name = "item_name", nullable = true, length = 255)
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @OneToMany(mappedBy = "option")
    private List<OptionItemEntity> items;

    public ItemEntity(String itemName, ProjectEntity entity) {
        this.itemName = itemName;
        this.project = entity;
    }
}
