package com.dkthanh.demo.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "demo", catalog = "")
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    private Integer categoryId;
    private String caregoryName;

    @Id
    @Column(name = "category_id", nullable = false)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "caregory_name", nullable = true, length = 255)
    public String getCaregoryName() {
        return caregoryName;
    }

    public void setCaregoryName(String caregoryName) {
        this.caregoryName = caregoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(caregoryName, that.caregoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, caregoryName);
    }
}
