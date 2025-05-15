package com.kh.bbs.web.form.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateForm {

  private Long boardId;  // ✅ 추가: 게시글 ID

  @NotBlank(message = "제목은 필수입니다.")
  private String title;

  @NotBlank(message = "작성자는 필수입니다.")
  private String writer;

  @NotBlank(message = "내용은 필수입니다.")
  private String content;

  private LocalDateTime createdAt;
}
