package com.ckja.realworld.service;

import com.ckja.realworld.model.CreateUserRequest;
import com.ckja.realworld.model.LoginRequest;
import com.ckja.realworld.model.UpdateCurrentUserRequest;
import com.ckja.realworld.model.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserAndAuthenticationService {

  ResponseEntity<UserResponse> processCreateUser(CreateUserRequest body);

  ResponseEntity<UserResponse> processLoginUser(LoginRequest body);

  ResponseEntity<UserResponse> checkLoginUserToken(String authorization);

  ResponseEntity<UserResponse> processUpdateCurrentUser(UpdateCurrentUserRequest body, String authorization);
}
