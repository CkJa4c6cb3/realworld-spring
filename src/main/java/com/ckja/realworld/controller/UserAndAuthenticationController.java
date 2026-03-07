package com.ckja.realworld.controller;

import com.ckja.realworld.api.UserAndAuthenticationApi;
import com.ckja.realworld.model.CreateUserRequest;
import com.ckja.realworld.model.LoginRequest;
import com.ckja.realworld.model.UpdateCurrentUserRequest;
import com.ckja.realworld.model.UserResponse;
import com.ckja.realworld.service.UserAndAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAndAuthenticationController implements UserAndAuthenticationApi {
  private final UserAndAuthenticationService userAndAuthenticationService;
  private final HttpServletRequest httpServletRequest;

  public UserAndAuthenticationController(
      UserAndAuthenticationService userAndAuthenticationService,
      HttpServletRequest httpServletRequest) {
    this.userAndAuthenticationService = userAndAuthenticationService;
    this.httpServletRequest = httpServletRequest;
  }

  @Override
  public ResponseEntity<UserResponse> createUser(CreateUserRequest body) {
    return userAndAuthenticationService.processCreateUser(body);
  }

  @Override
  public ResponseEntity<UserResponse> login(LoginRequest body) {
    return userAndAuthenticationService.processLoginUser(body);
  }

  @Override
  public ResponseEntity<UserResponse> getCurrentUser() {
    String authorization = httpServletRequest.getHeader("Authorization");
    return userAndAuthenticationService.checkLoginUserToken(authorization);
  }

  @Override
  public ResponseEntity<UserResponse> updateCurrentUser(UpdateCurrentUserRequest body) {
    String authorization = httpServletRequest.getHeader("Authorization");
    return userAndAuthenticationService.processUpdateCurrentUser(body, authorization);
  }
}
