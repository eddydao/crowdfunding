package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "project", schema = "demo", catalog = "")
public class ProjectEntity {
    private Integer projectId;
    private String projectName;
    private Integer teamId;
    private String projectShortDes;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private Double goal;
    private Double pledged;
    private Integer investorCount;
    private Integer projectStatusId;
    private Integer recommended;
    private Integer categoryId;

    @Id
    @Column(name = "project_id", nullable = false)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "project_name", nullable = true, length = 255)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "team_id", nullable = true)
    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "project_short_des", nullable = true, length = 255)
    public String getProjectShortDes() {
        return projectShortDes;
    }

    public void setProjectShortDes(String projectShortDes) {
        this.projectShortDes = projectShortDes;
    }

    @Basic
    @Column(name = "start_date", nullable = true)
    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "goal", nullable = true, precision = 0)
    public Double getGoal() {
        return goal;
    }

    public void setGoal(Double goal) {
        this.goal = goal;
    }

    @Basic
    @Column(name = "pledged", nullable = true, precision = 0)
    public Double getPledged() {
        return pledged;
    }

    public void setPledged(Double pledged) {
        this.pledged = pledged;
    }

    @Basic
    @Column(name = "investor_count", nullable = true)
    public Integer getInvestorCount() {
        return investorCount;
    }

    public void setInvestorCount(Integer investorCount) {
        this.investorCount = investorCount;
    }

    @Basic
    @Column(name = "project_status_id", nullable = true)
    public Integer getProjectStatusId() {
        return projectStatusId;
    }

    public void setProjectStatusId(Integer projectStatusId) {
        this.projectStatusId = projectStatusId;
    }

    @Basic
    @Column(name = "recommended", nullable = true)
    public Integer getRecommended() {
        return recommended;
    }

    public void setRecommended(Integer recommended) {
        this.recommended = recommended;
    }

    @Basic
    @Column(name = "category_id", nullable = true)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectEntity that = (ProjectEntity) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(teamId, that.teamId) &&
                Objects.equals(projectShortDes, that.projectShortDes) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(goal, that.goal) &&
                Objects.equals(pledged, that.pledged) &&
                Objects.equals(investorCount, that.investorCount) &&
                Objects.equals(projectStatusId, that.projectStatusId) &&
                Objects.equals(recommended, that.recommended) &&
                Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, teamId, projectShortDes, startDate, endDate, goal, pledged, investorCount, projectStatusId, recommended, categoryId);
    }
}
