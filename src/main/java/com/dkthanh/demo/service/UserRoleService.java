package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.UserRoleRepository;
import com.dkthanh.demo.domain.RoleEntity;
import com.dkthanh.demo.domain.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository repo;

    public List<RoleEntity> getRoles(Integer uid) {
        return repo.findRoleByUid(uid);
    }

    public UserRoleEntity insertNewAccountRole(UserRoleEntity userRoleEntity) {
        return repo.save(userRoleEntity);
    }
}
