package com.dkthanh.demo.service;

import com.dkthanh.demo.domain.RoleEntity;
import com.dkthanh.demo.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity acc = this.userService.findUserByUsername(username);
        if (acc == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        List<RoleEntity> roles = userRoleService.getRoles(acc.getId());
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roles != null) {
            for (RoleEntity role : roles) {
                GrantedAuthority autority = new SimpleGrantedAuthority(role.getRoleName());
                grantList.add(autority);
            }
        }

        UserDetails userDetails = new User(acc.getUsername(), //
                acc.getPassword(), grantList);
        return userDetails;
    }
}
