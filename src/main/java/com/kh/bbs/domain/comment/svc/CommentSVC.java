package com.kh.bbs.domain.comment.svc;

import com.kh.bbs.domain.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentSVC {

  /**
   * 댓글 등록
   * @param comment 저장할 댓글
   * @return 저장된 댓글
   */
  Comment save(Comment comment);

  /**
   * 게시글의 댓글 목록 조회
   * @param boardId 게시글 ID
   * @return 댓글 목록
   */
  List<Comment> findByBoardId(Long boardId);

  /**
   * 댓글 단건 조회
   * @param commentId 댓글 ID
   * @return 댓글 Optional
   */
  Optional<Comment> findById(Long commentId);

  /**
   * 댓글 수정
   * @param commentId 댓글 ID
   * @param content 수정할 내용
   */
  void update(Long commentId, String content);

  /**
   * 댓글 삭제
   * @param commentId 댓글 ID
   */
  void delete(Long commentId);
}
