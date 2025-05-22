package com.kh.bbs.domain.comment.dao;

import com.kh.bbs.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentDAOImpl implements CommentDAO {

  private final NamedParameterJdbcTemplate template;

  /**
   * 새로운 댓글을 저장한다.
   * 시퀀스를 통해 comment_id를 자동 생성하고,
   * 저장된 댓글 정보를 반환한다.
   * @param comment 저장할 댓글 객체 (boardId, content, writer 포함)
   * @return 저장된 댓글 객체 (commentId, createdAt 포함)
   */
  @Override
  public Comment save(Comment comment) {
    StringBuffer sql = new StringBuffer();
    sql.append("INSERT INTO board_comment ");
    sql.append("(comment_id, board_id, content, writer, created_at, updated_at) ");
    sql.append("VALUES (comment_comment_id_seq.nextval, :boardId, :content, :writer, SYSTIMESTAMP, SYSTIMESTAMP)");

    Map<String, Object> param = new HashMap<>();
    param.put("boardId", comment.getBoardId());
    param.put("content", comment.getContent());
    param.put("writer", comment.getWriter());

    template.update(sql.toString(), param);

    Long savedId = template.queryForObject(
        "SELECT comment_comment_id_seq.currval FROM dual",
        new MapSqlParameterSource(),
        Long.class
    );

    comment.setCommentId(savedId);
    comment.setCreatedAt(LocalDateTime.now());
    comment.setUpdatedAt(LocalDateTime.now());

    return comment;
  }

  /**
   * 게시글 댓글 목록 조회
   * @param boardId 게시글 넘버
   * @return 해당 게시글의 댓글 목록
   */
  @Override
  public List<Comment> findByBoardId(Long boardId) {
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT comment_id, board_id, content, writer, created_at, updated_at ");
    sql.append("FROM board_comment ");
    sql.append("WHERE board_id = :boardId ");
    sql.append("ORDER BY created_at ASC");

    Map<String, Object> param = Map.of("boardId", boardId);

    return template.query(sql.toString(), param, (rs, rowNum) -> {
      Comment comment = new Comment();
      comment.setCommentId(rs.getLong("comment_id"));
      comment.setBoardId(rs.getLong("board_id"));
      comment.setContent(rs.getString("content"));
      comment.setWriter(rs.getString("writer"));
      comment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
      comment.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
      return comment;
    });
  }

  /**
   * 댓글조회...필요할까???
   * @param commentId 조회할 댓글 넘버
   * @return
   */
  @Override
  public Optional<Comment> findById(Long commentId) {
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT comment_id, board_id, content, writer, created_at, updated_at ");
    sql.append("FROM board_comment ");
    sql.append("WHERE comment_id = :commentId");

    Map<String, Object> param = Map.of("commentId", commentId);

    List<Comment> result = template.query(sql.toString(), param, (rs, rowNum) -> {
      Comment comment = new Comment();
      comment.setCommentId(rs.getLong("comment_id"));
      comment.setBoardId(rs.getLong("board_id"));
      comment.setContent(rs.getString("content"));
      comment.setWriter(rs.getString("writer"));
      comment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
      comment.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
      return comment;
    });

    return result.stream().findFirst();
  }

  /**
   * 댓글 수정
   * @param commentId 수정할 댓글의 넘버
   * @param content 수정된 댓글
   */
  @Override
  public void update(Long commentId, String content) {
    StringBuffer sql = new StringBuffer();
    sql.append("UPDATE board_comment ");
    sql.append("SET content = :content, ");
    sql.append("    updated_at = SYSTIMESTAMP ");
    sql.append("WHERE comment_id = :commentId");

    Map<String, Object> param = new HashMap<>();
    param.put("commentId", commentId);
    param.put("content", content);

    template.update(sql.toString(), param);
  }

  /**
   * 댓글 삭제
   * @param commentId 삭제할 댓글 넘버
   */
  @Override
  public void delete(Long commentId) {
    StringBuffer sql = new StringBuffer();
    sql.append("DELETE FROM board_comment ");
    sql.append("WHERE comment_id = :commentId");

    Map<String, Object> param = Map.of("commentId", commentId);

    template.update(sql.toString(), param);
  }
}