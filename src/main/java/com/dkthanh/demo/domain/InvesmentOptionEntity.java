package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "invesment_option", schema = "demo", catalog = "")
public class InvesmentOptionEntity {
    private Integer optionId;
    private Integer projectId;
    private String optionName;
    private String optionDescription;
    private Integer fundMax;
    private Integer fundMin;

    @Id
    @Column(name = "option_id", nullable = false)
    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @Basic
    @Column(name = "project_id", nullable = false)
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "option_name", nullable = true, length = 255)
    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    @Basic
    @Column(name = "option_description", nullable = true, length = 255)
    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    @Basic
    @Column(name = "fund_max", nullable = true, precision = 0)
    public Integer getFundMax() {
        return fundMax;
    }

    public void setFundMax(Integer fundMax) {
        this.fundMax = fundMax;
    }

    @Basic
    @Column(name = "fund_min", nullable = true, precision = 0)
    public Integer getFundMin() {
        return fundMin;
    }

    public void setFundMin(Integer fundMin) {
        this.fundMin = fundMin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvesmentOptionEntity that = (InvesmentOptionEntity) o;
        return Objects.equals(optionId, that.optionId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(optionName, that.optionName) &&
                Objects.equals(optionDescription, that.optionDescription) &&
                Objects.equals(fundMax, that.fundMax) &&
                Objects.equals(fundMin, that.fundMin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId, projectId, optionName, optionDescription, fundMax, fundMin);
    }
}
