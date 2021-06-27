package com.dkthanh.demo.dao;

import com.dkthanh.demo.domain.CommentEntity;
import com.dkthanh.demo.domain.CommentEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, CommentEntityPK> {

    List<CommentEntity> findByProjectIdAndSectionId(Integer projectId, Integer sectionId);

    List<CommentEntity> findAllByProjectId(Integer projectId);
}
