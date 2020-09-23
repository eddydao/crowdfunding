package com.dkthanh.demo.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
@Data
@ToString
public class UserRole implements Serializable {

    @EmbeddedId
    private UserRoleId userRoleId;

    
}
