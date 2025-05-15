package com.kh.bbs.web.form.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetailForm {
  private Long boardId;
  private String title;
  private String writer;
  private String content;
  private LocalDateTime createdAt;
}
