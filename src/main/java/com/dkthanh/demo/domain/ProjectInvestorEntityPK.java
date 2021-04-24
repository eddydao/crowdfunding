package com.dkthanh.demo.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable

public class ProjectInvestorEntityPK implements Serializable {
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "investion_option_id", nullable = false)
    private Integer investionOptionId;


    @Id
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    @Id
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Column(name = "investion_option_id", nullable = false)
    @Id
    public Integer getInvestionOptionId() {
        return investionOptionId;
    }

    public void setInvestionOptionId(Integer investionOptionId) {
        this.investionOptionId = investionOptionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectInvestorEntityPK that = (ProjectInvestorEntityPK) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(investionOptionId, that.investionOptionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, projectId, investionOptionId);
    }
}
