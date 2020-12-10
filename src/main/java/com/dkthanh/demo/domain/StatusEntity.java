package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "status", schema = "demo", catalog = "")
public class StatusEntity {
    private Integer projectStatusId;
    private String statusName;

    @Id
    @Column(name = "project_status_id", nullable = false)
    public Integer getProjectStatusId() {
        return projectStatusId;
    }

    public void setProjectStatusId(Integer projectStatusId) {
        this.projectStatusId = projectStatusId;
    }

    @Basic
    @Column(name = "status_name", nullable = true, length = 255)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEntity that = (StatusEntity) o;
        return Objects.equals(projectStatusId, that.projectStatusId) &&
                Objects.equals(statusName, that.statusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectStatusId, statusName);
    }
}
