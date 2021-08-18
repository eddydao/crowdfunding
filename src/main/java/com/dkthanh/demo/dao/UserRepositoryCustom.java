package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.dto.UserDTO;

import java.util.List;

public interface UserRepositoryCustom  {
    public List<UserDTO> getListAdminUserFullInfo();
}
