package com.dkthanh.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "material_type", schema = "demo", catalog = "")
@Data
public class MaterialTypeEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = true, length = 255)
    private String name;



    @OneToMany(mappedBy = "materialType")
    private List<MaterialEntity> materials;

}
