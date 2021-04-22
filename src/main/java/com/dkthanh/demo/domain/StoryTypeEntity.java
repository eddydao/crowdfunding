package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "story_type", schema = "demo", catalog = "")
public class StoryTypeEntity {
    private Integer storyTypeId;
    private String storyTypeName;

    @Id
    @Column(name = "story_type_id", nullable = false)
    public Integer getStoryTypeId() {
        return storyTypeId;
    }

    public void setStoryTypeId(Integer storyTypeId) {
        this.storyTypeId = storyTypeId;
    }

    @Basic
    @Column(name = "story_type_name", nullable = true, length = 255)
    public String getStoryTypeName() {
        return storyTypeName;
    }

    public void setStoryTypeName(String storyTypeName) {
        this.storyTypeName = storyTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoryTypeEntity that = (StoryTypeEntity) o;
        return Objects.equals(storyTypeId, that.storyTypeId) &&
                Objects.equals(storyTypeName, that.storyTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storyTypeId, storyTypeName);
    }
}
