package com.dkthanh.demo.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.annotations.Many;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
@Data
@ToString
@IdClass(UserRole.PrimaryKeys.class)
public class UserRole implements Serializable {
    @Data
    public static class PrimaryKeys implements Serializable{
        private Integer userId;
        private Integer roleId;
    }

    @Id
    @NotNull
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @NotNull
    @Column(name = "role_id")
    private Integer roleId;

    public UserRole() {
    }

    public UserRole(@NotNull Integer userId, @NotNull Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
