package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.MaterialTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MaterialTypeService {

    @Autowired
    private MaterialTypeRepository repository;


}
