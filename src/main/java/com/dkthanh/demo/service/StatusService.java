package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.StatusRepository;
import com.dkthanh.demo.domain.StatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatusService {

    @Autowired
    private StatusRepository repository;
    public List<StatusEntity> getAllStatus(){
        return repository.findAll();
    }
}
