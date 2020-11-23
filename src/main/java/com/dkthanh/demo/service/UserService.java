package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.RoleRepository;
import com.dkthanh.demo.dao.UserRepository;
import com.dkthanh.demo.dao.UserRoleRepository;
import com.dkthanh.demo.domain.NewUserDTO;
import com.dkthanh.demo.domain.Role;
import com.dkthanh.demo.domain.User;
import com.dkthanh.demo.domain.UserRole;
import com.dkthanh.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public User saveUser(NewUserDTO userDTO){
        String uid = userDTO.getUserName();
        String encryptPass = this.passwordEncoder.encode(userDTO.getPassword());
        User user =  new User();
        try{
            user = userRepository.save(new User(uid, encryptPass));

            UserRole  userRole = userRoleRepository.save(new UserRole(user.getUserId(), Constant.Roles.getById(2).getId()));
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
