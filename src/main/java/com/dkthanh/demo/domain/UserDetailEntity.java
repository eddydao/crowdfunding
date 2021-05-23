package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user_detail", schema = "demo", catalog = "")
@Data
@ToString
public class UserDetailEntity {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "first_name", nullable = true, length = 255)
    private String firstName;
    @Column(name = "last_name", nullable = true, length = 255)
    private String lastName;
    @Column(name = "email", nullable = true, length = 255)
    private String email;
    @Column(name = "project_supported", nullable = true, length = 255)
    private String projectSupported;
    @Column(name = "total_amount", nullable = true, length = 25)
    private Long totalAmount;
    @Column(name = "address", nullable = true, length = 255)
    private String address;
    @Column(name = "country_id", nullable = true)
    private Integer countryId;
    @Column(name = "phone_num", nullable = true, length = 15)
    private String phoneNum;

    @OneToOne(mappedBy = "userDetail")
    private UserEntity user;
}
