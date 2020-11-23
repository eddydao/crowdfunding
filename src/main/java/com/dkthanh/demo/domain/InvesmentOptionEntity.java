package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "invesment_option", schema = "demo", catalog = "")
@IdClass(InvesmentOptionEntityPK.class)
public class InvesmentOptionEntity {
    private Integer invesmentOptionId;
    private Integer projectId;
    private Integer invesmentCatalogId;
    private String optionName;
    private String optionDescription;

    @Id
    @Column(name = "invesment_option_id", nullable = false)
    public Integer getInvesmentOptionId() {
        return invesmentOptionId;
    }

    public void setInvesmentOptionId(Integer invesmentOptionId) {
        this.invesmentOptionId = invesmentOptionId;
    }

    @Id
    @Column(name = "project_id", nullable = false)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Id
    @Column(name = "invesment_catalog_id", nullable = false)
    public Integer getInvesmentCatalogId() {
        return invesmentCatalogId;
    }

    public void setInvesmentCatalogId(Integer invesmentCatalogId) {
        this.invesmentCatalogId = invesmentCatalogId;
    }

    @Basic
    @Column(name = "option_name", nullable = true, length = 255)
    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    @Basic
    @Column(name = "option_description", nullable = true, length = 255)
    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvesmentOptionEntity that = (InvesmentOptionEntity) o;
        return Objects.equals(invesmentOptionId, that.invesmentOptionId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(invesmentCatalogId, that.invesmentCatalogId) &&
                Objects.equals(optionName, that.optionName) &&
                Objects.equals(optionDescription, that.optionDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invesmentOptionId, projectId, invesmentCatalogId, optionName, optionDescription);
    }
}
