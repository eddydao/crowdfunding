package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.RoleEntity;

import java.util.List;

public interface UserRoleRepositoryCustom {
    public List<RoleEntity> findRoleByUid(Integer uid);
}
