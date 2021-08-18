package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", schema = "demo", catalog = "")
@Data
@ToString
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username", nullable = false, length = 255)
    private String username;
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    public UserEntity() {
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private UserDetailEntity userDetail;


    @OneToMany(mappedBy = "user")
    private List<ProjectEntity> projects;

    @OneToMany(mappedBy = "user")
    private List<Package> packages;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "role_id"))
    private List<RoleEntity> roles;
}
