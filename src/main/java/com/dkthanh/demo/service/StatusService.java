package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.StatusRepository;
import com.dkthanh.demo.domain.StatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatusService {

    @Autowired
    private StatusRepository repository;
    public List<StatusEntity> getAllStatus(){
        return repository.findAll();
    }

    public StatusEntity getStatusById(Integer id){
        Optional<StatusEntity> op = repository.findById(id);
        if(op.isPresent()){
            return op.get();
        }
        return null;
    }
}
