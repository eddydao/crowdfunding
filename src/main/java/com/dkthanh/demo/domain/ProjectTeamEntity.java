package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "project_team", schema = "demo", catalog = "")
@IdClass(ProjectTeamEntityPK.class)
public class ProjectTeamEntity {
    private Integer teamId;
    private Integer projectId;
    private Integer userId;

    @Id
    @Column(name = "team_id", nullable = false)
    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTeamEntity that = (ProjectTeamEntity) o;
        return Objects.equals(teamId, that.teamId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, projectId, userId);
    }
}
