package com.kh.bbs.domain.board.dao;

import com.kh.bbs.domain.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardDAO {
  //게시글 저장
  Long save(Board board);

  //게시글 조회 (단건)
  Optional<Board> findById(Long boardId);

  //게시글 전체 조회
  List<Board> findAll();

  //게시글수정
  int update(Long boardId, Board board);

  //게시글삭제
  int deleteById(Long boardId);
  int deleteByIds(List<Long> boardIds);
}
