package com.kh.bbs.domain.board.dao;

import com.kh.bbs.domain.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class BoardDAOImplTest {

  @Autowired
  BoardDAO boardDAO;

   @Test
  void findByIdTest() {
    Long testId = 1L;

    Optional<Board> optionalBoard = boardDAO.findById(testId);

    if (optionalBoard.isPresent()) {
      Board board = optionalBoard.get();
      System.out.println("조회 성공: " + board);
    } else {
      System.out.println("해당 ID의 게시글 없음");
    }
  }

  @Test
  void findAllTest() {
    List<Board> list = boardDAO.findAll();
    for (Board board : list) {
      System.out.println("게시글: " + board);
    }
  }
}