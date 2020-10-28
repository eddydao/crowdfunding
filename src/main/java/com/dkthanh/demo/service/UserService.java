package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.RoleRepository;
import com.dkthanh.demo.dao.UserRepository;
import com.dkthanh.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User findUserById(Integer id){
        User user = null;
        try {
            user = userRepository.findById(id).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public User saveUser(User user){
        User newUser = userRepository.save(user);

        Role role = roleRepository.save();
        return null;
    }
}
