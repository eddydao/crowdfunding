package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.RoleEntity;
import com.dkthanh.demo.domain.UserRoleEntity;

import java.util.List;

public interface UserRoleRepositoryCustom {
    public List<RoleEntity> findRoleByUid(Integer uid);

    int countUserByRole(Integer roleId);

    List<UserRoleEntity> findUserListByRole(Integer roleid);
}
