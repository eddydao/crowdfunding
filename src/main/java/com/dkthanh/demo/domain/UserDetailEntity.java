package com.dkthanh.demo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_detail", schema = "demo", catalog = "")
public class UserDetailEntity {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String projectSupported;
    private String totalAmount;
    private String address;
    private Integer countryId;
    private String phoneNum;

    @Id
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "project_supported", nullable = true, length = 255)
    public String getProjectSupported() {
        return projectSupported;
    }

    public void setProjectSupported(String projectSupported) {
        this.projectSupported = projectSupported;
    }

    @Basic
    @Column(name = "total_amount", nullable = true, length = 255)
    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "country_id", nullable = true)
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "phone_num", nullable = true, length = 15)
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailEntity that = (UserDetailEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(projectSupported, that.projectSupported) &&
                Objects.equals(totalAmount, that.totalAmount) &&
                Objects.equals(address, that.address) &&
                Objects.equals(countryId, that.countryId) &&
                Objects.equals(phoneNum, that.phoneNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, email, projectSupported, totalAmount, address, countryId, phoneNum);
    }
}
