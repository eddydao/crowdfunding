package com.dkthanh.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "material")
@Data
public class MaterialEntity {
    @Id
    @Column(name = "material_id", nullable = false)
    private Integer materialId;
    @Column(name = "description", nullable = true, length = 255)
    private String description;
    @Column(name = "path", nullable = true, length = 255)
    private String path;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "material_type_id")
    private MaterialTypeEntity materialType;


}
