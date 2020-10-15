package com.dkthanh.demo.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.annotations.Many;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
@Data
@ToString
public class UserRole implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    private Role role;
}
