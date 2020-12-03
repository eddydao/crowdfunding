package com.dkthanh.demo.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MaterialEntityPK implements Serializable {
    private Integer materialId;
    private Integer projectId;
    private Integer materialTypeId;

    @Column(name = "material_id", nullable = false)
    @Id
    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    @Column(name = "project_id", nullable = false)
    @Id
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Column(name = "material_type_id", nullable = false)
    @Id
    public Integer getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialEntityPK that = (MaterialEntityPK) o;
        return Objects.equals(materialId, that.materialId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(materialTypeId, that.materialTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, projectId, materialTypeId);
    }
}
