package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.CategoryRepository;
import com.dkthanh.demo.domain.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<CategoryEntity> getAllCategory(){
        return categoryRepository.findAll();
    }

    public CategoryEntity getCategoryById(Integer id){
        Optional<CategoryEntity> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        }
        return null;
    }

    public boolean deleteCategory(Integer categoryId){
        categoryRepository.deleteById(categoryId);

        Optional<CategoryEntity> test = categoryRepository.findById(categoryId);
        if(test.isPresent()){
            return false;
        }
        return true;
    }
}
