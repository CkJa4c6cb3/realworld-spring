package com.ckja.realworld.repository;

import com.ckja.realworld.model.Tag;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepository {

  private final JdbcTemplate jdbcTemplate;

  public TagRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Tag> findAll() {
    return jdbcTemplate.query(
        "SELECT name FROM tags ORDER BY name ASC",
        (rs, rowNum) -> new Tag(rs.getString("name")));
  }
}
