package com.kh.bbs.domain.comment.dao;

import com.kh.bbs.domain.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDAO {
  Comment save(Comment comment);
  List<Comment> findByBoardId(Long boardId);
  Optional<Comment> findById(Long commentId);
  void update(Long commentId, String content);
  void delete(Long commentId);
}

