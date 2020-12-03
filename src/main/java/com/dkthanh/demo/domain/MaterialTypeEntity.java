package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "material_type", schema = "demo", catalog = "")
public class MaterialTypeEntity {
    private Integer materialTypeId;
    private String materialTypeName;

    @Id
    @Column(name = "material_type_id", nullable = false)
    public Integer getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    @Basic
    @Column(name = "material_type_name", nullable = true, length = 255)
    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialTypeEntity that = (MaterialTypeEntity) o;
        return Objects.equals(materialTypeId, that.materialTypeId) &&
                Objects.equals(materialTypeName, that.materialTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialTypeId, materialTypeName);
    }
}
