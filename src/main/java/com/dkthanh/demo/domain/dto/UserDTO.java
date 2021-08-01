package com.dkthanh.demo.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UserDTO  implements Serializable {
    public static final String USER_DTO_MAP = "userDtoMap";
    public static final String USER_FULL_DTO_MAP = "userFullDtoMap";

    private Integer userId;
    private String userName;
    private String userPassword;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNum;
    private String oldPassword;
    private String confirmPassword;
    private String roleName;
    private Integer roleId;

    public UserDTO() {
    }

    public UserDTO(Integer userId, String userName, String firstName, String lastName, String email, String address, String phoneNum) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNum = phoneNum;
    }

    public UserDTO(Integer userId, String userName, String email, String address, String phoneNum, String roleName, Integer roleId) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.phoneNum = phoneNum;
        this.roleName = roleName;
        this.roleId = roleId;
    }
}
