package com.dkthanh.demo.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CommentEntityPK implements Serializable {
    private Integer commentId;
    private Integer projectId;
    private Integer userId;

    @Column(name = "comment_id", nullable = false)
    @Id
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Column(name = "project_id", nullable = false)
    @Id
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Column(name = "user_id", nullable = false)
    @Id
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntityPK that = (CommentEntityPK) o;
        return Objects.equals(commentId, that.commentId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, projectId, userId);
    }
}
