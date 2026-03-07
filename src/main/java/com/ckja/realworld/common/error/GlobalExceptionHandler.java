package com.ckja.realworld.common.error;

import com.ckja.realworld.model.GenericErrorModel;
import com.ckja.realworld.model.RealWorldApiException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RealWorldApiException.class)
  public ResponseEntity<GenericErrorModel> handleApiException(RealWorldApiException ex) {
    return ResponseEntity
        .status(ex.getStatus())
        .body(new GenericErrorModel().putErrorsItem(ex.getErrorKey(), List.of(ex.getMessage())));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<GenericErrorModel> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    List<String> messages = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(FieldError::getDefaultMessage)
        .collect(Collectors.toList());
    if (messages.isEmpty()) {
      messages = List.of("入力値が不正です");
    }
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(new GenericErrorModel().putErrorsItem("body", messages));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GenericErrorModel> handleException(Exception ex) {
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(new GenericErrorModel().putErrorsItem("body", List.of("Unexpected error")));
  }
}
