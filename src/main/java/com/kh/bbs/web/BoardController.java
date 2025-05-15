package com.kh.bbs.web;

import com.kh.bbs.domain.board.svc.BoardSVC;
import com.kh.bbs.domain.entity.Board;
import com.kh.bbs.web.form.board.DetailForm;
import com.kh.bbs.web.form.board.SaveForm;
import com.kh.bbs.web.form.board.UpdateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
  // 게시글 목록
  @GetMapping
  public String findAll(Model model){
    List<Board> list = boardSVC.findAll();
    model.addAttribute("list", list);
    return "board/list"; // templates/board/list.html
  }

  // 게시글 등록 화면
  @GetMapping("/add")
  public String addForm(Model model) {
    model.addAttribute("saveForm",new SaveForm());
    return "board/add"; // templates/board/add.html
  }

  // 게시글 등록 처리
  @PostMapping("/add")
  public String save(
      @Valid @ModelAttribute SaveForm saveForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ) {
    //  유효성 검사 실패 시 다시 입력 폼
    if (bindingResult.hasErrors()) {
      return "board/add";
    }

    Board board = new Board();
    board.setTitle(saveForm.getTitle());
    board.setWriter(saveForm.getWriter());
    board.setContent(saveForm.getContent());

    Long id = boardSVC.save(board);

    // 리다이렉트
    redirectAttributes.addAttribute("id", id);
    return "redirect:/board/view/{id}";
  }


  // 게시글 단건 조회 (상세화면)
  @GetMapping("/view/{id}")
  public String findById(
      @PathVariable("id") Long id,
      Model model) {
    Board board = boardSVC.findById(id).orElseThrow();

    DetailForm detailForm = new DetailForm();
    detailForm.setBoardId(board.getBoardId());
    detailForm.setTitle(board.getTitle());
    detailForm.setWriter(board.getWriter());
    detailForm.setContent(board.getContent());
    detailForm.setCreatedAt(board.getCreatedAt());

    model.addAttribute("detailForm", detailForm);
    return "board/detail";
  }


  // 게시글 삭제
  @GetMapping("/delete/{id}")
  public String deleteById(@PathVariable("id") Long id){
    boardSVC.deleteById(id);
    return "redirect:/board";
  }

  // 게시글 수정
  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable("id") Long id, Model model) {
    Board board = boardSVC.findById(id).orElseThrow();

    UpdateForm updateForm = new UpdateForm();
    updateForm.setBoardId(board.getBoardId());
    updateForm.setTitle(board.getTitle());
    updateForm.setWriter(board.getWriter());
    updateForm.setContent(board.getContent());
    updateForm.setCreatedAt(board.getCreatedAt());

    model.addAttribute("updateForm", updateForm);
    return "board/edit2";
  }

  //게시글 수정처리
  @PostMapping("/edit/{id}")
  public String updateById(
      @PathVariable("id") Long id,
      @Valid @ModelAttribute UpdateForm updateForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ) {
    log.info("bindingResult.hasErrors() = {}", bindingResult.hasErrors());

//    if (bindingResult.hasErrors()) {
//      return "board/edit2";
//    }

    Board board = new Board();
    board.setBoardId(id);
    board.setTitle(updateForm.getTitle());
    board.setWriter(updateForm.getWriter());
    board.setContent(updateForm.getContent());

    boardSVC.update(id, board);

    redirectAttributes.addAttribute("id", id);
    return "redirect:/board/view/{id}";
  }


}