package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "project", schema = "demo", catalog = "")
@Data
@ToString
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
    private Double goal;
    @Column(name = "pledged", nullable = true, precision = 0)
    private Double pledged;
    @Column(name = "investor_count", nullable = true)
    private Integer investorCount;
    @Column(name = "recommended", nullable = true)
    private Integer recommended;

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
    @JoinColumn(name = "id")
    private UserEntity user;


    @OneToMany(mappedBy = "project")
    private List<MaterialEntity> materials;

    @OneToMany(mappedBy = "project")
    private List<OptionEntity> options;

    @OneToOne
    @JoinColumn(name = "story_id")
    private StoryEntity story;


}
