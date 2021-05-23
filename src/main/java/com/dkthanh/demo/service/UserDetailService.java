package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.UserDetailRepository;
import com.dkthanh.demo.domain.UserDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service

public class UserDetailService {
    @Autowired
    private UserDetailRepository repository;
    public UserDetailEntity getUserByEmail(String email){
        return null;
    }

    public UserDetailEntity getUserDetailByUserId(Integer id){
        Optional<UserDetailEntity> optionalUserDetailEntity = repository.findById(id);
        if(optionalUserDetailEntity.isPresent()){
            return optionalUserDetailEntity.get();
        }
        return null;
    }
}
