package com.kh.bbs.domain.board.dao;

import com.kh.bbs.domain.entity.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardDAOImpl implements BoardDAO{

  private final NamedParameterJdbcTemplate template;

  private RowMapper<Board> boardRowMapper() {
    return (rs, rowNum) -> {
      Board board = new Board();
      board.setBoardId(rs.getLong("board_id"));
      board.setTitle(rs.getString("title"));
      board.setContent(rs.getString("content"));
      board.setWriter(rs.getString("writer"));

      Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
      Timestamp updatedAtTimestamp = rs.getTimestamp("updated_at");

      board.setCreatedAt(createdAtTimestamp != null ? createdAtTimestamp.toLocalDateTime() : null);
      board.setUpdatedAt(updatedAtTimestamp != null ? updatedAtTimestamp.toLocalDateTime() : null);

      return board;
    };
  }

  /**
   * 게시글 저장
   * @param board 저장할 게시글
   * @return 생성된 게시글 ID
   */
  @Override
  public Long save(Board board) {
    StringBuffer sql = new StringBuffer();
    sql.append("INSERT INTO board (board_id, title, content, writer, created_at, updated_at) ");
    sql.append("     VALUES (board_board_id_seq.nextval, :title, :content, :writer, systimestamp, systimestamp) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(board);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(), param, keyHolder, new String[] {"board_id"});

    Number key = keyHolder.getKey();
    return key != null ? key.longValue() : null;
  }

  /**
   * 게시글 단건 조회
   * @param boardId 조회할 게시글 ID
   * @return 조회된 게시글
   */
  @Override
  public Optional<Board> findById(Long boardId) {
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT board_id, title, content, writer, created_at, updated_at ");
    sql.append("FROM board WHERE board_id = :board_id ");

    SqlParameterSource param = new MapSqlParameterSource().addValue("board_id", boardId);

    Board board = null;
    try {
      board = template.queryForObject(
          sql.toString(),
          param,
          boardRowMapper()
      );
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }

    return Optional.of(board);
  }

  /**
   * 게시글 목록 조회
   * @return 게시글 리스트 (최신순)
   */
  @Override
  public List<Board> findAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("   SELECT board_id, title, content, writer, created_at, updated_at ");
    sql.append("     FROM board  ");
    sql.append(" ORDER BY board_id DESC ");

    List<Board> list = template.query(sql.toString(), boardRowMapper());

    return list;
    }

  /**
   * 게시글 수정
   * @param boardId 수정할 게시글 ID
   * @param board 수정할 게시글 내용
   * @return 수정된 행 수
   */
  @Override
  public int update(Long boardId, Board board) {
    StringBuffer sql = new StringBuffer();
    sql.append("UPDATE board ");
    sql.append("   SET title = :title, ");
    sql.append("       content = :content, ");
    sql.append("       writer = :writer, ");
    sql.append("       updated_at = systimestamp ");
    sql.append(" WHERE board_id = :boardId ");

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("title", board.getTitle())
        .addValue("content", board.getContent())
        .addValue("writer", board.getWriter())
        .addValue("boardId", boardId);

    int rows = template.update(sql.toString(), param);

    return rows;
  }

  /**
   * 게시글 단건 삭제
   * @param boardId 삭제할 게시글 번호
   * @return 삭제 건수
   */
  @Override
  public int deleteById(Long boardId) {
    StringBuffer sql = new StringBuffer();
    sql.append("DELETE FROM board ");
    sql.append(" WHERE board_id = :boardId ");

    Map<String, Long> param = Map.of("boardId", boardId);
    return template.update(sql.toString(), param);
  }

  /**
   * 게시글 다건 삭제
   * @param boardIds 삭제할 게시글 번호 목록
   * @return 삭제 건수
   */
  @Override
  public int deleteByIds(List<Long> boardIds) {
    StringBuffer sql = new StringBuffer();
    sql.append("DELETE FROM board ");
    sql.append(" WHERE board_id IN ( :boardIds ) ");

    Map<String, List<Long>> param = Map.of("boardIds", boardIds);
    return template.update(sql.toString(), param);
  }


}
