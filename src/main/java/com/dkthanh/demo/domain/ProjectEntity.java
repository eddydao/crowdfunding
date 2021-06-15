package com.dkthanh.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "project")
@Data
public class ProjectEntity {
    @Id
    @Column(name = "project_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    @Column(name = "project_name", nullable = true, length = 255)
    private String projectName;
    @Column(name = "project_short_des", nullable = true, length = 255)
    private String projectShortDes;
    @Column(name = "start_date", nullable = true)
    private OffsetDateTime startDate;
    @Column(name = "end_date", nullable = true)
    private OffsetDateTime endDate;
    @Column(name = "goal", nullable = true, precision = 0)
    private Long goal;
    @Column(name = "pledged", nullable = true, precision = 0)
    private Long pledged;
    @Column(name = "investor_count", nullable = true)
    private Integer investorCount;
    @Column(name = "recommended", nullable = true)
    private Integer recommended;
    @Column(name = "thumbnail_path", nullable = true)
    private String thumbnailPath;
    @Column(name="submit_date", nullable = true)
    private String submitDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity projectStatus;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @OneToMany(mappedBy = "project")
    private List<MaterialEntity> materials;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<OptionEntity> options;

    @OneToMany(mappedBy = "project")
    private List<Package> packages;

    @OneToMany(mappedBy = "project")
    private List<ItemEntity> items;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private StoryEntity story;


}
