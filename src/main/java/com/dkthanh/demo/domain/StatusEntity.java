package com.dkthanh.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "status", schema = "demo", catalog = "")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StatusEntity {
    @Id
    @Column(name = "status_id", nullable = false)
    private Integer statusId;
    @Column(name = "name", nullable = true, length = 255)
    private String name;

    @OneToMany(mappedBy = "projectStatus")
    private List<ProjectEntity> projects;

}
