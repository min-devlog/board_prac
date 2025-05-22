package com.kh.bbs.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
 public class Comment {
  private Long commentId;         // 댓글 ID
  private Long boardId;           // 게시글 ID (FK)
  private String content;         // 댓글 내용
  private String writer;          // 작성자
  private LocalDateTime createdAt; // 작성일시
  private LocalDateTime updatedAt; // 수정일시
}
