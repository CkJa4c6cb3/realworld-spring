package com.ckja.realworld.repository;

import com.ckja.realworld.model.User;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserAndAuthenticationRepository {

  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<User> mapUserResult = (rs, rowNum) -> {
    User user = new User();
    user.setEmail(rs.getString("email"));
    user.setUsername(rs.getString("username"));
    user.setToken("");
    user.setBio(JsonNullable.of(rs.getString("bio")));
    user.setImage(JsonNullable.of(rs.getString("image")));
    return user;
  };

  public UserAndAuthenticationRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<User> getUserByEmail(String email) {
    return jdbcTemplate.query(
        "SELECT email, username, bio, image FROM users WHERE email = ?",
        mapUserResult,
        email);
  }

  public List<User> getUserByUsername(String username) {
    return jdbcTemplate.query(
        "SELECT email, username, bio, image FROM users WHERE username = ?",
        mapUserResult,
        username);
  }

  public User createUser(String email, String username, String hashedPassword) {
    jdbcTemplate.update(
        "INSERT INTO users (email, username, password) VALUES (?, ?, ?)",
        email,
        username,
        hashedPassword);
    return getUserByEmail(email).get(0);
  }

  public List<String> getPasswordByEmail(String email) {
    return jdbcTemplate.query(
        "SELECT password FROM users WHERE email = ?",
        (rs, rowNum) -> rs.getString("password"),
        email);
  }
}
