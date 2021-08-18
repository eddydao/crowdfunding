package com.dkthanh.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role", schema = "demo", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @Column(name = "role_id", nullable = false)
    private Integer roleId;
    @Column(name = "role_name", nullable = true, length = 255)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    Set<UserEntity> users;

    public RoleEntity(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}
