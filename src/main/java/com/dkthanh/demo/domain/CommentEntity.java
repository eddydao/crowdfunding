package com.dkthanh.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment", schema = "demo", catalog = "")
@IdClass(CommentEntityPK.class)
@Data
public class CommentEntity {
    @Id
    @Column(name = "project_id", nullable = false)
    private Integer projectId;
    @Id
    @Column(name = "section_id", nullable = false)
    private Integer sectionId;
    private String commentText;
    private String tab;
    private Timestamp timestamp;
    private Integer isClose;

}
