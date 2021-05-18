package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "story_type", schema = "demo", catalog = "")
@Data
@ToString
public class StoryTypeEntity {
    @Id
    @Column(name = "story_type_id", nullable = false)
    private Integer storyTypeId;
    @Column(name = "story_type_name", nullable = true, length = 255)
    private String storyTypeName;

    @OneToMany(mappedBy = "storyType")
    private List<StoryEntity> stories;
}
