package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.UserDetailRepository;
import com.dkthanh.demo.domain.UserDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserDetailEntity save(UserDetailEntity entity){
        return repository.save(entity);
    }
}
