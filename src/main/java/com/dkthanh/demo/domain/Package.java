package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "package", schema = "demo", catalog = "")
@Data
@ToString
public class Package {
    @Id
    @Column(name = "package_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packageId;

    private Integer pledged;
    private Timestamp timestamp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "option_id")
    private OptionEntity option;

}
