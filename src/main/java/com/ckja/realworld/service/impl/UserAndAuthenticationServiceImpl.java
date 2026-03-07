package com.ckja.realworld.service.impl;

import com.ckja.realworld.common.security.JwtTokenProvider;
import com.ckja.realworld.common.util.PasswordEncodeUtil;
import com.ckja.realworld.model.CreateUserRequest;
import com.ckja.realworld.model.LoginRequest;
import com.ckja.realworld.model.RealWorldApiException;
import com.ckja.realworld.model.UpdateCurrentUserRequest;
import com.ckja.realworld.model.UpdateUser;
import com.ckja.realworld.model.User;
import com.ckja.realworld.model.UserResponse;
import com.ckja.realworld.repository.UserAndAuthenticationRepository;
import com.ckja.realworld.service.UserAndAuthenticationService;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserAndAuthenticationServiceImpl implements UserAndAuthenticationService {

  private final UserAndAuthenticationRepository userAndAuthenticationRepository;
  private final JwtTokenProvider jwtTokenProvider;

  public UserAndAuthenticationServiceImpl(
      UserAndAuthenticationRepository userAndAuthenticationRepository,
      JwtTokenProvider jwtTokenProvider) {
    this.userAndAuthenticationRepository = userAndAuthenticationRepository;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public ResponseEntity<UserResponse> processCreateUser(CreateUserRequest body) {
    String email = body.getUser().getEmail();
    String username = body.getUser().getUsername();
    String rawPassword = body.getUser().getPassword();

    if (isUserExist(userAndAuthenticationRepository.getUserByEmail(email))) {
      throw new RealWorldApiException(HttpStatus.CONFLICT, "すでに存在するメールアドレスです");
    }

    if (isUserExist(userAndAuthenticationRepository.getUserByUsername(username))) {
      throw new RealWorldApiException(HttpStatus.CONFLICT, "すでに存在するユーザー名です");
    }

    String hashedPassword = PasswordEncodeUtil.encode(rawPassword);

    try {
      User createdUser = userAndAuthenticationRepository.createUser(email, username, hashedPassword);
      createdUser.setToken(jwtTokenProvider.generateToken(createdUser.getEmail()));
      return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(createdUser));
    } catch (DuplicateKeyException ex) {
      throw new RealWorldApiException(HttpStatus.CONFLICT, "すでに存在するユーザーです", ex);
    }
  }

  @Override
  public ResponseEntity<UserResponse> processLoginUser(LoginRequest body) {
    String email = body.getUser().getEmail();
    String rawPassword = body.getUser().getPassword();

    List<User> users = userAndAuthenticationRepository.getUserByEmail(email);
    if (!isUserExist(users)) {
      throw new RealWorldApiException(HttpStatus.UNAUTHORIZED, "メールアドレスまたはパスワードが間違っています");
    }

    List<String> encodedPasswords = userAndAuthenticationRepository.getPasswordByEmail(email);
    if (encodedPasswords.isEmpty() || !PasswordEncodeUtil.matches(rawPassword, encodedPasswords.get(0))) {
      throw new RealWorldApiException(HttpStatus.UNAUTHORIZED, "メールアドレスまたはパスワードが間違っています");
    }

    User loginUser = users.get(0);
    loginUser.setToken(jwtTokenProvider.generateToken(loginUser.getEmail()));
    return ResponseEntity.ok(new UserResponse(loginUser));
  }

  @Override
  public ResponseEntity<UserResponse> checkLoginUserToken(String authorization) {
    User currentUser = resolveCurrentUser(authorization);
    String token = extractToken(authorization);
    currentUser.setToken(token);
    return ResponseEntity.ok(new UserResponse(currentUser));
  }

  @Override
  public ResponseEntity<UserResponse> processUpdateCurrentUser(UpdateCurrentUserRequest body, String authorization) {
    User currentUser = resolveCurrentUser(authorization);
    UpdateUser updateUser = body.getUser();

    String newEmail = updateUser.getEmail();
    String newUsername = updateUser.getUsername();
    String rawPassword = updateUser.getPassword();
    JsonNullable<String> bio = updateUser.getBio();
    JsonNullable<String> image = updateUser.getImage();

    if (newEmail != null
        && !newEmail.equals(currentUser.getEmail())
        && isUserExist(userAndAuthenticationRepository.getUserByEmail(newEmail))) {
      throw new RealWorldApiException(HttpStatus.CONFLICT, "すでに存在するメールアドレスです");
    }

    if (newUsername != null
        && !newUsername.equals(currentUser.getUsername())
        && isUserExist(userAndAuthenticationRepository.getUserByUsername(newUsername))) {
      throw new RealWorldApiException(HttpStatus.CONFLICT, "すでに存在するユーザー名です");
    }

    if (!hasAnyUpdatableField(updateUser)) {
      throw new RealWorldApiException(HttpStatus.UNPROCESSABLE_ENTITY, "更新対象の項目がありません");
    }

    String hashedPassword = rawPassword == null ? null : PasswordEncodeUtil.encode(rawPassword);
    try {
      User updatedUser = userAndAuthenticationRepository.updateUser(
          currentUser.getEmail(),
          newEmail,
          newUsername,
          hashedPassword,
          bio,
          image);
      updatedUser.setToken(jwtTokenProvider.generateToken(updatedUser.getEmail()));
      return ResponseEntity.ok(new UserResponse(updatedUser));
    } catch (DuplicateKeyException ex) {
      throw new RealWorldApiException(HttpStatus.CONFLICT, "すでに存在するユーザーです", ex);
    }
  }

  private User resolveCurrentUser(String authorization) {
    String token = extractToken(authorization);
    if (!jwtTokenProvider.validateToken(token)) {
      throw new RealWorldApiException(HttpStatus.UNAUTHORIZED, "無効なトークンです");
    }

    String email = jwtTokenProvider.getSubject(token);
    List<User> users = userAndAuthenticationRepository.getUserByEmail(email);
    if (!isUserExist(users)) {
      throw new RealWorldApiException(HttpStatus.UNAUTHORIZED, "ユーザーが見つかりません");
    }
    return users.get(0);
  }

  private boolean isUserExist(List<User> users) {
    return users != null && !users.isEmpty();
  }

  private boolean hasAnyUpdatableField(UpdateUser updateUser) {
    return updateUser.getEmail() != null
        || updateUser.getUsername() != null
        || updateUser.getPassword() != null
        || (updateUser.getBio() != null && updateUser.getBio().isPresent())
        || (updateUser.getImage() != null && updateUser.getImage().isPresent());
  }

  private String extractToken(String authorization) {
    if (authorization == null || !authorization.startsWith("Token ")) {
      throw new RealWorldApiException(HttpStatus.UNAUTHORIZED, "Authorizationヘッダーが不正です");
    }

    String token = authorization.substring("Token ".length()).trim();
    if (token.isEmpty()) {
      throw new RealWorldApiException(HttpStatus.UNAUTHORIZED, "トークンが空です");
    }
    return token;
  }
}
