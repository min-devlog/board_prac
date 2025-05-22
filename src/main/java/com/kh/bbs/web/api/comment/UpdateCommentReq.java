package com.kh.bbs.web.api.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCommentReq {

  @NotBlank(message = "내용은 비워둘 수 없습니다.")
  private String content;
}
