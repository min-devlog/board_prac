package com.kh.bbs.web.form.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SaveForm {

  @NotBlank(message = "제목은 필수입니다.")
  private String title;

  @NotBlank(message = "작성자는 필수입니다.")
  @Size(max = 10, message = "작성자는 10자 이내여야 합니다.")
  private String writer;

  @NotBlank(message = "내용은 필수입니다.")
  private String content;
}
