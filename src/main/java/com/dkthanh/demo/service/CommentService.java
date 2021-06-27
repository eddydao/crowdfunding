package com.dkthanh.demo.service;

import com.dkthanh.demo.dao.CommentRepository;
import com.dkthanh.demo.domain.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;


    public CommentEntity getCommentByProjectIdAndSectionId(Integer projectId, Integer sectionId){
        return commentRepository.findByProjectIdAndSectionId(projectId, sectionId).get(0);
    }

    public CommentEntity saveComment(CommentEntity commentEntity){
        return commentRepository.save(commentEntity);
    }

    public List<CommentEntity> getAllCommentsByProjectId(Integer projectId){
        return commentRepository.findAllByProjectId(projectId);
    }
}
