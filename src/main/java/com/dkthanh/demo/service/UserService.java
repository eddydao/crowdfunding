package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.RoleRepository;
import com.dkthanh.demo.dao.UserRepository;
import com.dkthanh.demo.dao.UserRoleRepository;
import com.dkthanh.demo.domain.NewUserDTO;
import com.dkthanh.demo.domain.UserEntity;
import com.dkthanh.demo.domain.UserRoleEntity;
import com.dkthanh.demo.domain.dto.UserDTO;
import com.dkthanh.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public List<UserEntity> getAllUser(){
        return userRepository.findAll();
    }

    public UserEntity findUserById(Integer id){
        UserEntity user = null;
        try {
            user = userRepository.findById(id).get();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public UserEntity findUserByUsername(String username){
        UserEntity user = null;
        try {
            Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
            if(optionalUserEntity.isPresent()){
                user = optionalUserEntity.get();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public UserEntity saveUser(NewUserDTO userDTO){
        String uid = userDTO.getUsername();
        String encryptPass = this.passwordEncoder.encode(userDTO.getPassword());
        UserEntity user =  new UserEntity();
        try{
            user = userRepository.save(new UserEntity(uid, encryptPass));

            UserRoleEntity userRole = userRoleRepository.save(new UserRoleEntity(user.getId(), Constant.Roles.getById(2).getId()));
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public UserEntity saveUser(UserEntity userEntity){
        UserEntity user = new UserEntity();
        try{
            user = userRepository.save(userEntity);
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public List<UserDTO> getListUserFullInfo(){
        return userRepository.getListUserFullInformation();
    }

}
