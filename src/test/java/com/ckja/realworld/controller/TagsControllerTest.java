package com.ckja.realworld.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ckja.realworld.service.TagService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class TagsControllerTest {

  @Test
  void getTagsReturnsTagsResponse() throws Exception {
    TagService tagService = new TagService() {
      @Override
      public List<String> getTags() {
        return List.of("java", "spring");
      }
    };

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new TagsController(tagService)).build();

    mockMvc.perform(get("/tags"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.tags[0]").value("java"))
        .andExpect(jsonPath("$.tags[1]").value("spring"));
  }
}
