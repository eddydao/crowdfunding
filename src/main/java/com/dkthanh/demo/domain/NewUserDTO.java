package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NewUserDTO {
    private String username;
    private String password;
    private String confirmPassword;
}
