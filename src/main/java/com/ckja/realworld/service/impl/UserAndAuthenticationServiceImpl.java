package com.ckja.realworld.service.impl;

import com.ckja.realworld.common.util.PasswordEncodeUtil;
import com.ckja.realworld.model.CreateUserRequest;
import com.ckja.realworld.model.LoginRequest;
import com.ckja.realworld.model.User;
import com.ckja.realworld.model.UserResponse;
import com.ckja.realworld.repository.UserAndAuthenticationRepository;
import com.ckja.realworld.service.UserAndAuthenticationService;
import java.util.List;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserAndAuthenticationServiceImpl implements UserAndAuthenticationService {

  private final UserAndAuthenticationRepository userAndAuthenticationRepository;

  public UserAndAuthenticationServiceImpl(
      UserAndAuthenticationRepository userAndAuthenticationRepository) {
    this.userAndAuthenticationRepository = userAndAuthenticationRepository;
  }

  @Override
  public ResponseEntity<UserResponse> processCreateUser(CreateUserRequest body) {
    String email = body.getUser().getEmail();
    String username = body.getUser().getUsername();
    String rawPassword = body.getUser().getPassword();

    if (isUserExist(userAndAuthenticationRepository.getUserByEmail(email))) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "すでに存在するメールアドレスです");
    }

    if (isUserExist(userAndAuthenticationRepository.getUserByUsername(username))) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "すでに存在するユーザー名です");
    }

    String hashedPassword = PasswordEncodeUtil.encode(rawPassword);

    try {
      User createdUser = userAndAuthenticationRepository.createUser(email, username, hashedPassword);
      return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(createdUser));
    } catch (DuplicateKeyException ex) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "すでに存在するユーザーです", ex);
    }
  }

  @Override
  public ResponseEntity<UserResponse> processLoginUser(LoginRequest body) {
    String email = body.getUser().getEmail();
    String rawPassword = body.getUser().getPassword();

    List<User> users = userAndAuthenticationRepository.getUserByEmail(email);
    if (!isUserExist(users)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "メールアドレスまたはパスワードが間違っています");
    }

    List<String> encodedPasswords = userAndAuthenticationRepository.getPasswordByEmail(email);
    if (encodedPasswords.isEmpty() || !PasswordEncodeUtil.matches(rawPassword, encodedPasswords.get(0))) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "メールアドレスまたはパスワードが間違っています");
    }

    return ResponseEntity.ok(new UserResponse(users.get(0)));
  }

  private boolean isUserExist(List<User> users) {
    return users != null && !users.isEmpty();
  }
}
