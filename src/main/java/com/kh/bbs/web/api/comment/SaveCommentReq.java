package com.kh.bbs.web.api.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveCommentReq {

  @NotNull(message = "게시글 ID는 필수입니다.")
  private Long boardId;

  @NotBlank(message = "작성자는 필수입니다.")
  private String writer;

  @NotBlank(message = "내용은 비워둘 수 없습니다.")
  private String content;
}
