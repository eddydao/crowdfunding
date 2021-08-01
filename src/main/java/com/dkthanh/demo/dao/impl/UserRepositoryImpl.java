package com.dkthanh.demo.dao.impl;

import com.dkthanh.demo.dao.UserRepositoryCustom;
import com.dkthanh.demo.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    @Autowired
    private EntityManager em;

    @Override
    public List<UserDTO> getListUserFullInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "SELECT\n" +
                "A.id\n" +
                ", A.username\n" +
                ", B.email\n" +
                ", B.address\n" +
                ", B.phone_num\n" +
                ", D.role_name\n" +
                ", C.role_id\n" +
                "FROM \n" +
                "user A \n" +
                "JOIN user_detail B ON A.id = B.user_id\n" +
                "LEFT JOIN user_role C ON A.id = C.user_id\n" +
                "LEFT JOIN role D on C.role_id = D.role_id\n" +
                "WHERE \n" +
                "1 = 1\n" );

        Query query = em.createNativeQuery(sb.toString(), UserDTO.USER_FULL_DTO_MAP);
        return query.getResultList();

    }
}
