package com.kh.bbs.domain.board.svc;

import com.kh.bbs.domain.board.dao.BoardDAO;
import com.kh.bbs.domain.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardSVCImpl implements BoardSVC{

  private final BoardDAO boardDAO;

  @Override
  public Long save(Board board) {
    return boardDAO.save(board);
  }

  @Override
  public Optional<Board> findById(Long boardId) {
    return boardDAO.findById(boardId);
  }

  @Override
  public List<Board> findAll() {
    return boardDAO.findAll();
  }

  @Override
  public int update(Long boardId, Board board) {
    return boardDAO.update(boardId, board);
  }

  @Override
  public int deleteById(Long boardId) {
    return boardDAO.deleteById(boardId);
  }

  @Override
  public int deleteByIds(List<Long> boardIds) {
    return boardDAO.deleteByIds(boardIds);
  }
}
