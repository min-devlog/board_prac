package com.kh.bbs.domain.board.svc;

import com.kh.bbs.domain.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardSVC {
  Long save(Board board);
  Optional<Board> findById(Long boardId);
  List<Board> findAll();
  int update(Long boardId, Board board);
  int deleteById(Long boardId);
  int deleteByIds(List<Long> boardIds);
}
