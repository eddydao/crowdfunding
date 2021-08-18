package com.dkthanh.demo.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CommentEntityPK implements Serializable {
    private Integer projectId;
    private Integer sectionId;

    @Column(name = "project_id", nullable = false)
    @Id
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Column(name = "section_id", nullable = false)
    @Id
    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntityPK that = (CommentEntityPK) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(sectionId, that.sectionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, sectionId);
    }
}
