package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.CountryRepository;
import com.dkthanh.demo.domain.CountryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository repository;

    public List<CountryEntity> getAllCountry(){
        return repository.findAll();
    }
}
