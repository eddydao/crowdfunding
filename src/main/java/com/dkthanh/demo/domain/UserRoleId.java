package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.net.InetAddress;

@Embeddable
@Data
@ToString
public class UserRoleId implements Serializable {
    private Integer userId;
    private Integer roleId;
}
