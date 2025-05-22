package com.kh.bbs.web.api.comment;

import com.kh.bbs.domain.comment.svc.CommentSVC;
import com.kh.bbs.domain.entity.Comment;
import com.kh.bbs.web.api.ApiResponse;
import com.kh.bbs.web.api.ApiResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentApi {

  private final CommentSVC commentSVC;

  // 댓글 등록
  @PostMapping
  public ApiResponse<Comment> save(@RequestBody @Valid SaveCommentReq req) {
    Comment comment = new Comment();
    comment.setBoardId(req.getBoardId());
    comment.setWriter(req.getWriter());
    comment.setContent(req.getContent());

    Comment saved = commentSVC.save(comment);
    return ApiResponse.of(ApiResponseCode.SUCCESS, saved);
  }

  // 게시글의 댓글 목록 조회
  @GetMapping("/{boardId}")
  public ApiResponse<List<Comment>> list(@PathVariable("boardId") Long boardId) {
    List<Comment> comments = commentSVC.findByBoardId(boardId);
    return ApiResponse.of(ApiResponseCode.SUCCESS, comments);
  }

  // 댓글 수정
  @PatchMapping("/{commentId}")
  public ApiResponse<Void> update(@PathVariable Long commentId,
                                  @RequestBody @Valid UpdateCommentReq req) {
    commentSVC.update(commentId, req.getContent());
    return ApiResponse.of(ApiResponseCode.SUCCESS);
  }

  // 댓글 삭제
  @DeleteMapping("/{commentId}")
  public ApiResponse<Void> delete(@PathVariable Long commentId) {
    commentSVC.delete(commentId);
    return ApiResponse.of(ApiResponseCode.SUCCESS);
  }
}
