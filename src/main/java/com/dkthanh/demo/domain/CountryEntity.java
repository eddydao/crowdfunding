package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country", schema = "demo", catalog = "")
@Data
@ToString
public class CountryEntity {
    @Id
    @Column(name = "country_id", nullable = false)
    private Integer countryId;
    @Column(name = "country_name", nullable = true, length = 255)
    private String countryName;

    @OneToMany(mappedBy = "country")
    private List<ProjectEntity> projects;
}
