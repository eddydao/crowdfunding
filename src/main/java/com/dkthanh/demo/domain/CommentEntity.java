package com.dkthanh.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment", schema = "demo", catalog = "")
@IdClass(CommentEntityPK.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
    @Id
    @Column(name = "project_id", nullable = false)
    private Integer projectId;
    @Id
    @Column(name = "section_id", nullable = false)
    private Integer sectionId;
    private String commentText;
    private Integer tab;
    private Timestamp timestamp;
    private Integer isClose;

    public CommentEntity(Integer projectId, Integer sectionId) {
        this.projectId = projectId;
        this.sectionId = sectionId;
    }
}
