package com.ckja.realworld.service.impl;

import com.ckja.realworld.model.Tag;
import com.ckja.realworld.repository.TagRepository;
import com.ckja.realworld.service.TagService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

  private final TagRepository tagRepository;

  public TagServiceImpl(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  @Override
  public List<String> getTags() {
    return tagRepository.findAll().stream()
        .map(Tag::name)
        .toList();
  }
}
