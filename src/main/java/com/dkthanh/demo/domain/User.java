package com.dkthanh.demo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@ToString
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer userId;


    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    public User(){}

    public User(String userName, String password){
        this.userName = userName;
        this.password =password;
    }

    public User(Integer id, String userName, String password){
        this.userId = id;
        this.userName = userName;
        this.password = password;
    }
}

