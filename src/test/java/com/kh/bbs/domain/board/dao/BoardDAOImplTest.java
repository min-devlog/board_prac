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

  @Test
  void saveTest() {
    Board board = new Board();
    board.setTitle("테스트 제목");
    board.setContent("테스트 내용");
    board.setWriter("테스터");

    Long savedId = boardDAO.save(board);
    System.out.println("등록된 게시글 ID: " + savedId);
  }

  @Test
  void updateTest() {
    Long targetId = 1L;  // 존재하는 ID 넣어야 함
    Board board = new Board();
    board.setTitle("수정된 제목");
    board.setContent("수정된 내용");
    board.setWriter("수정된 작성자");

    int result = boardDAO.update(targetId, board);
    System.out.println("수정된 건수: " + result);
  }

  @Test
  void deleteByIdTest() {
    Long targetId = 1L;  // 존재하는 ID 넣어야 함
    int result = boardDAO.deleteById(targetId);
    System.out.println("삭제된 건수: " + result);
  }



}