package com.ckja.realworld.model;

import org.springframework.http.HttpStatus;

public class RealWorldApiException extends RuntimeException {

  private final HttpStatus status;
  private final String errorKey;

  public RealWorldApiException(HttpStatus status, String message) {
    this(status, "body", message);
  }

  public RealWorldApiException(HttpStatus status, String errorKey, String message) {
    super(message);
    this.status = status;
    this.errorKey = errorKey;
  }

  public RealWorldApiException(HttpStatus status, String message, Throwable cause) {
    this(status, "body", message, cause);
  }

  public RealWorldApiException(HttpStatus status, String errorKey, String message, Throwable cause) {
    super(message, cause);
    this.status = status;
    this.errorKey = errorKey;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getErrorKey() {
    return errorKey;
  }
}
