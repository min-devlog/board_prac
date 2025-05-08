package com.kh.bbs.web;

import com.kh.bbs.domain.board.svc.BoardSVC;
import com.kh.bbs.domain.entity.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

  private final BoardSVC boardSVC;

  // 게시글 등록 화면
  @GetMapping("/add")
  public String addForm() {
    return "board/add"; // templates/board/add.html
  }

  // 게시글 등록 처리
  @PostMapping("/add")
  public String save(@ModelAttribute Board board, RedirectAttributes redirectAttributes){
    Long id = boardSVC.save(board);
    redirectAttributes.addAttribute("id", id);
    return "redirect:/board/{id}";
  }

  // 게시글 단건 조회 (상세화면)
  @GetMapping("/{id}")
  public String findById(@PathVariable Long id, Model model){
    Board board = boardSVC.findById(id).orElseThrow();
    model.addAttribute("board", board);
    return "board/detail"; // templates/board/detail.html
  }

  // 게시글 목록
  @GetMapping
  public String findAll(Model model){
    List<Board> list = boardSVC.findAll();
    model.addAttribute("list", list);
    return "board/list"; // templates/board/list.html
  }

  // 게시글 삭제
  @GetMapping("/{id}/del")
  public String deleteById(@PathVariable Long id){
    boardSVC.deleteById(id);
    return "redirect:/board";
  }
}