package com.ckja.realworld.repository;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepository {

  private final JdbcTemplate jdbcTemplate;

  public TagRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<String> findAllNames() {
    return jdbcTemplate.queryForList(
        "SELECT name FROM tags ORDER BY name ASC",
        String.class);
  }
}
