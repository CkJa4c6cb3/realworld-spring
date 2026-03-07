package com.ckja.realworld.controller;

import com.ckja.realworld.api.UserAndAuthenticationApi;
import com.ckja.realworld.model.CreateUserRequest;
import com.ckja.realworld.model.LoginRequest;
import com.ckja.realworld.model.UserResponse;
import com.ckja.realworld.service.UserAndAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAndAuthenticationController implements UserAndAuthenticationApi {
  private final UserAndAuthenticationService userAndAuthenticationService;

  public UserAndAuthenticationController(UserAndAuthenticationService userAndAuthenticationService) {
    this.userAndAuthenticationService = userAndAuthenticationService;
  }

  @Override
  public ResponseEntity<UserResponse> createUser(CreateUserRequest body) {
    return userAndAuthenticationService.processCreateUser(body);
  }

  @Override
  public ResponseEntity<UserResponse> login(LoginRequest body) {
    return userAndAuthenticationService.processLoginUser(body);
  }
}
