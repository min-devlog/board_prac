package com.kh.bbs.domain.comment.svc;

import com.kh.bbs.domain.comment.dao.CommentDAO;
import com.kh.bbs.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentSVCImpl implements CommentSVC {

  private final CommentDAO commentDAO;

  @Override
  public Comment save(Comment comment) {
    return commentDAO.save(comment);
  }

  @Override
  public List<Comment> findByBoardId(Long boardId) {
    return commentDAO.findByBoardId(boardId);
  }

  @Override
  public Optional<Comment> findById(Long commentId) {
    return commentDAO.findById(commentId);
  }

  @Override
  public void update(Long commentId, String content) {
    commentDAO.update(commentId, content);
  }

  @Override
  public void delete(Long commentId) {
    commentDAO.delete(commentId);
  }
}
