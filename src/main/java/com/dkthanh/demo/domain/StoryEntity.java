package com.dkthanh.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "story", schema = "demo", catalog = "")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoryEntity {
    @Id
    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "story_des", nullable = true, length = -1)
    private String storyDes;

    @OneToOne(mappedBy = "story")
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "story_type_id")
    private StoryTypeEntity storyType;
}
