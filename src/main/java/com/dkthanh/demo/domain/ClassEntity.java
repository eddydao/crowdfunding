package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "class", schema = "demo", catalog = "")
@Data
@ToString
public class ClassEntity {

    @Id
    @Column(name = "class_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classId;
    @Column(name = "class_name", nullable = true, length = 255)
    private String className;

    @OneToMany(mappedBy = "classEntity")
    private List<StudentEntity> students;
}
