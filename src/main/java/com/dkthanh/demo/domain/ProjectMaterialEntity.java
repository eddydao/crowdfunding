package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_material", schema = "demo", catalog = "")
@IdClass(ProjectMaterialEntityPK.class)
public class ProjectMaterialEntity {
    private Integer projectId;
    private Integer materialTypeId;
    private String description;
    private String link;

    @Id
    @Column(name = "project_id", nullable = false)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Id
    @Column(name = "material_type_id", nullable = false)
    public Integer getMaterialTypeId() {
        return materialTypeId;
    }

    public void setMaterialTypeId(Integer materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "link", nullable = true, length = 255)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMaterialEntity that = (ProjectMaterialEntity) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(materialTypeId, that.materialTypeId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, materialTypeId, description, link);
    }
}
