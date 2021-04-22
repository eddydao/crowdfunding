package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "story", schema = "demo", catalog = "")
public class StoryEntity {
    private Integer storyId;
    private String storyDes;
    private Integer projectId;
    private Integer storyTypeId;

    @Id
    @Column(name = "story_id", nullable = false)
    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    @Basic
    @Column(name = "story_des", nullable = true, length = -1)
    public String getStoryDes() {
        return storyDes;
    }

    public void setStoryDes(String storyDes) {
        this.storyDes = storyDes;
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
    @Column(name = "story_type_id", nullable = true)
    public Integer getStoryTypeId() {
        return storyTypeId;
    }

    public void setStoryTypeId(Integer storyTypeId) {
        this.storyTypeId = storyTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoryEntity that = (StoryEntity) o;
        return Objects.equals(storyId, that.storyId) &&
                Objects.equals(storyDes, that.storyDes) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(storyTypeId, that.storyTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storyId, storyDes, projectId, storyTypeId);
    }
}
