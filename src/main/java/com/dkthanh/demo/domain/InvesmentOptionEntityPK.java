package com.dkthanh.demo.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class InvesmentOptionEntityPK implements Serializable {
    private Integer invesmentOptionId;
    private Integer projectId;
    private Integer invesmentCatalogId;

    @Column(name = "invesment_option_id", nullable = false)
    @Id
    public Integer getInvesmentOptionId() {
        return invesmentOptionId;
    }

    public void setInvesmentOptionId(Integer invesmentOptionId) {
        this.invesmentOptionId = invesmentOptionId;
    }

    @Column(name = "project_id", nullable = false)
    @Id
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Column(name = "invesment_catalog_id", nullable = false)
    @Id
    public Integer getInvesmentCatalogId() {
        return invesmentCatalogId;
    }

    public void setInvesmentCatalogId(Integer invesmentCatalogId) {
        this.invesmentCatalogId = invesmentCatalogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvesmentOptionEntityPK that = (InvesmentOptionEntityPK) o;
        return Objects.equals(invesmentOptionId, that.invesmentOptionId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(invesmentCatalogId, that.invesmentCatalogId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invesmentOptionId, projectId, invesmentCatalogId);
    }
}
