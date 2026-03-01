package com.ckja.realworld.model;

import java.net.URI;
import java.util.Objects;
import com.ckja.realworld.model.InlineObject4ArticlesInner;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * InlineObject4
 */

@JsonTypeName("inline_object_4")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-01T21:06:09.127675+09:00[Asia/Tokyo]", comments = "Generator version: 7.20.0")
public class InlineObject4 {

  @Valid
  private List<@Valid InlineObject4ArticlesInner> articles = new ArrayList<>();

  private Integer articlesCount;

  public InlineObject4() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InlineObject4(List<@Valid InlineObject4ArticlesInner> articles, Integer articlesCount) {
    this.articles = articles;
    this.articlesCount = articlesCount;
  }

  public InlineObject4 articles(List<@Valid InlineObject4ArticlesInner> articles) {
    this.articles = articles;
    return this;
  }

  public InlineObject4 addArticlesItem(InlineObject4ArticlesInner articlesItem) {
    if (this.articles == null) {
      this.articles = new ArrayList<>();
    }
    this.articles.add(articlesItem);
    return this;
  }

  /**
   * Get articles
   * @return articles
   */
  @NotNull @Valid 
  @Schema(name = "articles", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("articles")
  public List<@Valid InlineObject4ArticlesInner> getArticles() {
    return articles;
  }

  public void setArticles(List<@Valid InlineObject4ArticlesInner> articles) {
    this.articles = articles;
  }

  public InlineObject4 articlesCount(Integer articlesCount) {
    this.articlesCount = articlesCount;
    return this;
  }

  /**
   * Get articlesCount
   * @return articlesCount
   */
  @NotNull 
  @Schema(name = "articlesCount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("articlesCount")
  public Integer getArticlesCount() {
    return articlesCount;
  }

  public void setArticlesCount(Integer articlesCount) {
    this.articlesCount = articlesCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineObject4 inlineObject4 = (InlineObject4) o;
    return Objects.equals(this.articles, inlineObject4.articles) &&
        Objects.equals(this.articlesCount, inlineObject4.articlesCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(articles, articlesCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineObject4 {\n");
    sb.append("    articles: ").append(toIndentedString(articles)).append("\n");
    sb.append("    articlesCount: ").append(toIndentedString(articlesCount)).append("\n");
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

