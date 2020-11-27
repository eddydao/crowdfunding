package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.UserRoleEntity;
import com.dkthanh.demo.domain.UserRoleEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleEntityPK> , UserRoleRepositoryCustom{


}
