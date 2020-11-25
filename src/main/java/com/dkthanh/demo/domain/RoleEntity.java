package com.dkthanh.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role", schema = "demo", catalog = "")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @Column(name = "role_id", nullable = false)
    private Integer roleId;
    @Basic
    @Column(name = "role_name", nullable = true, length = 255)
    private String roleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }
}
