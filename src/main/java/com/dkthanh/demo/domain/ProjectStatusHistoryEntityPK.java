package com.dkthanh.demo.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProjectStatusHistoryEntityPK implements Serializable {
    private Integer projectId;
    private Integer projectStatusId;
    private Integer projectStatusHistoryId;

    @Column(name = "project_id", nullable = false)
    @Id
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Column(name = "project_status_id", nullable = false)
    @Id
    public Integer getProjectStatusId() {
        return projectStatusId;
    }

    public void setProjectStatusId(Integer projectStatusId) {
        this.projectStatusId = projectStatusId;
    }

    @Column(name = "project_status_history_id", nullable = false)
    @Id
    public Integer getProjectStatusHistoryId() {
        return projectStatusHistoryId;
    }

    public void setProjectStatusHistoryId(Integer projectStatusHistoryId) {
        this.projectStatusHistoryId = projectStatusHistoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectStatusHistoryEntityPK that = (ProjectStatusHistoryEntityPK) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(projectStatusId, that.projectStatusId) &&
                Objects.equals(projectStatusHistoryId, that.projectStatusHistoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectStatusId, projectStatusHistoryId);
    }
}
