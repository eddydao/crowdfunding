package com.dkthanh.demo.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "project_investor", schema = "demo", catalog = "")
public class ProjectInvestorEntity {
    private Integer projInvestorId;
    private Integer userId;
    private Integer projectId;
    private Integer investionOptionId;
    private BigDecimal pledged;
    private Timestamp timestamp;

    @Basic
    @Column(name = "proj_investor_id", nullable = true)
    public Integer getProjInvestorId() {
        return projInvestorId;
    }

    public void setProjInvestorId(Integer projInvestorId) {
        this.projInvestorId = projInvestorId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "project_id", nullable = true)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "investion_option_id", nullable = true)
    public Integer getInvestionOptionId() {
        return investionOptionId;
    }

    public void setInvestionOptionId(Integer investionOptionId) {
        this.investionOptionId = investionOptionId;
    }

    @Basic
    @Column(name = "pledged", nullable = true, precision = 2)
    public BigDecimal getPledged() {
        return pledged;
    }

    public void setPledged(BigDecimal pledged) {
        this.pledged = pledged;
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
        ProjectInvestorEntity that = (ProjectInvestorEntity) o;
        return Objects.equals(projInvestorId, that.projInvestorId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(investionOptionId, that.investionOptionId) &&
                Objects.equals(pledged, that.pledged) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projInvestorId, userId, projectId, investionOptionId, pledged, timestamp);
    }
}
