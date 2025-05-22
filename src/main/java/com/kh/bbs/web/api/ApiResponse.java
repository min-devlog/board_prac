package com.kh.bbs.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 간단한 API 응답 포맷
 * 항상 {code: SUCCESS, data: ...} 형태로 응답됨
 */
@Data
@AllArgsConstructor(staticName = "of")
public class ApiResponse<T> {
  private ApiResponseCode code;
  private T data;

  // data 없이 상태만 보내고 싶을 때 사용
  public static <T> ApiResponse<T> of(ApiResponseCode code) {
    return of(code, null);
  }
}
