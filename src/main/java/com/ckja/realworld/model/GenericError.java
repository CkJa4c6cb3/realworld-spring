package com.ckja.realworld.model;

import java.util.List;
import java.util.Map;

import jakarta.annotation.Generated;

/**
 * GenericError
 *
 * OpenAPI 上の response 名に対応する読みやすい別名クラスです。
 */
@Generated(value = "manual", date = "2026-03-03T00:00:00+09:00[Asia/Tokyo]")
public class GenericError extends GenericErrorModel {

  public GenericError() {
    super();
  }

  public GenericError(Map<String, List<String>> errors) {
    super(errors);
  }
}
