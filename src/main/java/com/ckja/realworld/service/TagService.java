package com.ckja.realworld.service;

import com.ckja.realworld.repository.TagRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TagService {

  private final TagRepository tagRepository;

  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  public List<String> getTags() {
    return tagRepository.findAllNames();
  }
}
