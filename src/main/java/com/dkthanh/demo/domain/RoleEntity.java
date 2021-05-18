package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role", schema = "demo", catalog = "")
@Data
@ToString
public class RoleEntity {
    @Id
    @Column(name = "role_id", nullable = false)
    private Integer roleId;
    @Column(name = "role_name", nullable = true, length = 255)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    Set<UserEntity> users;
}
