package com.dkthanh.demo.dao.impl;

import com.dkthanh.demo.dao.UserRoleRepositoryCustom;
import com.dkthanh.demo.domain.RoleEntity;
import com.dkthanh.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRoleRepositoryCustomImpl implements UserRoleRepositoryCustom {
    @Autowired
    private EntityManager em;


    @Override
    public List<RoleEntity> findRoleByUid(Integer uid) {
        StringBuilder sb = new StringBuilder();
        sb.append("select role_id from user_role where user_id= :uid");
        Query sql = em.createNativeQuery(sb.toString(), Tuple.class);
        sql.setParameter("uid", uid);
        List<Tuple> listTuple = sql.getResultList();
        List<RoleEntity> roles = new ArrayList<RoleEntity>();
        for (Tuple tuple : listTuple) {
            RoleEntity role = new RoleEntity();
            Integer roleId = tuple.get("role_id", Integer.class);
            role.setRoleId(roleId);
            role.setRoleName(Constant.Roles.getById(roleId).getName());
            roles.add(role);
        }
        return roles;
    }
}
