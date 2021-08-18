package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.UserRoleRepository;
import com.dkthanh.demo.domain.RoleEntity;
import com.dkthanh.demo.domain.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserRoleService {

    @Autowired
    private UserRoleRepository repo;

    public List<RoleEntity> getRoles(Integer uid) {
        return repo.findRoleByUid(uid);
    }

    public UserRoleEntity insertNewAccountRole(UserRoleEntity userRoleEntity) {
        return repo.save(userRoleEntity);
    }

//    public UserRoleEntity insertNewAccountRole(Integer userId, Integer roleId) {
//
//        return repo.save();
//    }

    public int countUserByRole(Integer roleId){
        return repo.countUserByRole(roleId);
    }
}
