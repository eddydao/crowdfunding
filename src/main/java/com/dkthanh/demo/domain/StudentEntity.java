package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Data
@ToString
public class StudentEntity {

    @Id
    @Column(name = "student_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    @Column(name = "student_name", nullable = true, length = 255)
    private String studentName;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;
}
