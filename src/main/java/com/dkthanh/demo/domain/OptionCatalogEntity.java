package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "option_catalog", schema = "demo", catalog = "")
public class OptionCatalogEntity {
    private Integer optionCatalogId;
    private String optionName;
    private Integer fundMin;
    private Integer fundMax;

    @Id
    @Column(name = "option_catalog_id", nullable = false)
    public Integer getOptionCatalogId() {
        return optionCatalogId;
    }

    public void setOptionCatalogId(Integer optionCatalogId) {
        this.optionCatalogId = optionCatalogId;
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
    @Column(name = "fund_min", nullable = true, precision = 0)
    public Integer getFundMin() {
        return fundMin;
    }

    public void setFundMin(Integer fundMin) {
        this.fundMin = fundMin;
    }

    @Basic
    @Column(name = "fund_max", nullable = true, precision = 0)
    public Integer getFundMax() {
        return fundMax;
    }

    public void setFundMax(Integer fundMax) {
        this.fundMax = fundMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionCatalogEntity that = (OptionCatalogEntity) o;
        return Objects.equals(optionCatalogId, that.optionCatalogId) &&
                Objects.equals(optionName, that.optionName) &&
                Objects.equals(fundMin, that.fundMin) &&
                Objects.equals(fundMax, that.fundMax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionCatalogId, optionName, fundMin, fundMax);
    }
}
