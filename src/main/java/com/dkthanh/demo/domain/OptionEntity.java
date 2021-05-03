package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "option", schema = "demo", catalog = "")
@Data
@ToString
public class OptionEntity {
    @Id
    @Column(name = "option_id", nullable = false)
    private Integer optionId;
    @Column(name = "option_name", nullable = true, length = 255)
    private String optionName;
    @Column(name = "option_description", nullable = true, length = 255)
    private String optionDescription;
    @Column(name = "fund_max", nullable = true, precision = 0)
    private Integer fundMax;
    @Column(name = "fund_min", nullable = true, precision = 0)
    private Integer fundMin;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @OneToMany(mappedBy = "option")
    private List<Package> packages;

    @OneToMany(mappedBy = "option")
    private List<ItemEntity> items;
}
