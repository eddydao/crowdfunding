package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "project_status_history", schema = "demo", catalog = "")
@IdClass(ProjectStatusHistoryEntityPK.class)
public class ProjectStatusHistoryEntity {
    private Integer projectId;
    private Integer projectStatusId;
    private Integer projectStatusHistoryId;
    private Timestamp timestamp;

    @Id
    @Column(name = "project_id", nullable = false)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Id
    @Column(name = "project_status_id", nullable = false)
    public Integer getProjectStatusId() {
        return projectStatusId;
    }

    public void setProjectStatusId(Integer projectStatusId) {
        this.projectStatusId = projectStatusId;
    }

    @Id
    @Column(name = "project_status_history_id", nullable = false)
    public Integer getProjectStatusHistoryId() {
        return projectStatusHistoryId;
    }

    public void setProjectStatusHistoryId(Integer projectStatusHistoryId) {
        this.projectStatusHistoryId = projectStatusHistoryId;
    }

    @Basic
    @Column(name = "timestamp", nullable = true)
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectStatusHistoryEntity that = (ProjectStatusHistoryEntity) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(projectStatusId, that.projectStatusId) &&
                Objects.equals(projectStatusHistoryId, that.projectStatusHistoryId) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectStatusId, projectStatusHistoryId, timestamp);
    }
}
