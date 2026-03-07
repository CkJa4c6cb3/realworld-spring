package com.ckja.realworld.model;

import java.net.URI;
import java.util.Objects;
import com.ckja.realworld.model.NewArticle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateArticleRequest
 */

@JsonTypeName("CreateArticle_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-01T21:06:09.127675+09:00[Asia/Tokyo]", comments = "Generator version: 7.20.0")
public class CreateArticleRequest {

  private NewArticle article;

  public CreateArticleRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateArticleRequest(NewArticle article) {
    this.article = article;
  }

  public CreateArticleRequest article(NewArticle article) {
    this.article = article;
    return this;
  }

  /**
   * Get article
   * @return article
   */
  @NotNull @Valid 
  @Schema(name = "article", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("article")
  public NewArticle getArticle() {
    return article;
  }

  public void setArticle(NewArticle article) {
    this.article = article;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateArticleRequest createArticleRequest = (CreateArticleRequest) o;
    return Objects.equals(this.article, createArticleRequest.article);
  }

  @Override
  public int hashCode() {
    return Objects.hash(article);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateArticleRequest {\n");
    sb.append("    article: ").append(toIndentedString(article)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(@Nullable Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

