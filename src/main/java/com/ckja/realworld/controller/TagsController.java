package com.ckja.realworld.controller;

import com.ckja.realworld.api.TagsApi;
import com.ckja.realworld.model.TagsResponse;
import com.ckja.realworld.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagsController implements TagsApi {

  private final TagService tagService;

  public TagsController(TagService tagService) {
    this.tagService = tagService;
  }

  @Override
  public ResponseEntity<TagsResponse> getTags() {
    return ResponseEntity.ok(new TagsResponse(tagService.getTags()));
  }
}
