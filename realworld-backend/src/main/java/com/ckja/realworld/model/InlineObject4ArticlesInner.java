package com.ckja.realworld.model;

import java.net.URI;
import java.util.Objects;
import com.ckja.realworld.model.Profile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * InlineObject4ArticlesInner
 */

@JsonTypeName("inline_object_4_articles_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-01T21:06:09.127675+09:00[Asia/Tokyo]", comments = "Generator version: 7.20.0")
public class InlineObject4ArticlesInner {

  private String slug;

  private String title;

  private String description;

  @Valid
  private List<String> tagList = new ArrayList<>();

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime updatedAt;

  private Boolean favorited;

  private Integer favoritesCount;

  private Profile author;

  public InlineObject4ArticlesInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public InlineObject4ArticlesInner(String slug, String title, String description, List<String> tagList, OffsetDateTime createdAt, OffsetDateTime updatedAt, Boolean favorited, Integer favoritesCount, Profile author) {
    this.slug = slug;
    this.title = title;
    this.description = description;
    this.tagList = tagList;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.favorited = favorited;
    this.favoritesCount = favoritesCount;
    this.author = author;
  }

  public InlineObject4ArticlesInner slug(String slug) {
    this.slug = slug;
    return this;
  }

  /**
   * Get slug
   * @return slug
   */
  @NotNull 
  @Schema(name = "slug", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("slug")
  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public InlineObject4ArticlesInner title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @NotNull 
  @Schema(name = "title", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public InlineObject4ArticlesInner description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  @NotNull 
  @Schema(name = "description", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public InlineObject4ArticlesInner tagList(List<String> tagList) {
    this.tagList = tagList;
    return this;
  }

  public InlineObject4ArticlesInner addTagListItem(String tagListItem) {
    if (this.tagList == null) {
      this.tagList = new ArrayList<>();
    }
    this.tagList.add(tagListItem);
    return this;
  }

  /**
   * Get tagList
   * @return tagList
   */
  @NotNull 
  @Schema(name = "tagList", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tagList")
  public List<String> getTagList() {
    return tagList;
  }

  public void setTagList(List<String> tagList) {
    this.tagList = tagList;
  }

  public InlineObject4ArticlesInner createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
   */
  @NotNull @Valid 
  @Schema(name = "createdAt", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public InlineObject4ArticlesInner updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

  /**
   * Get updatedAt
   * @return updatedAt
   */
  @NotNull @Valid 
  @Schema(name = "updatedAt", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("updatedAt")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public InlineObject4ArticlesInner favorited(Boolean favorited) {
    this.favorited = favorited;
    return this;
  }

  /**
   * Get favorited
   * @return favorited
   */
  @NotNull 
  @Schema(name = "favorited", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("favorited")
  public Boolean getFavorited() {
    return favorited;
  }

  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }

  public InlineObject4ArticlesInner favoritesCount(Integer favoritesCount) {
    this.favoritesCount = favoritesCount;
    return this;
  }

  /**
   * Get favoritesCount
   * @return favoritesCount
   */
  @NotNull 
  @Schema(name = "favoritesCount", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("favoritesCount")
  public Integer getFavoritesCount() {
    return favoritesCount;
  }

  public void setFavoritesCount(Integer favoritesCount) {
    this.favoritesCount = favoritesCount;
  }

  public InlineObject4ArticlesInner author(Profile author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
   */
  @NotNull @Valid 
  @Schema(name = "author", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("author")
  public Profile getAuthor() {
    return author;
  }

  public void setAuthor(Profile author) {
    this.author = author;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineObject4ArticlesInner inlineObject4ArticlesInner = (InlineObject4ArticlesInner) o;
    return Objects.equals(this.slug, inlineObject4ArticlesInner.slug) &&
        Objects.equals(this.title, inlineObject4ArticlesInner.title) &&
        Objects.equals(this.description, inlineObject4ArticlesInner.description) &&
        Objects.equals(this.tagList, inlineObject4ArticlesInner.tagList) &&
        Objects.equals(this.createdAt, inlineObject4ArticlesInner.createdAt) &&
        Objects.equals(this.updatedAt, inlineObject4ArticlesInner.updatedAt) &&
        Objects.equals(this.favorited, inlineObject4ArticlesInner.favorited) &&
        Objects.equals(this.favoritesCount, inlineObject4ArticlesInner.favoritesCount) &&
        Objects.equals(this.author, inlineObject4ArticlesInner.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(slug, title, description, tagList, createdAt, updatedAt, favorited, favoritesCount, author);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineObject4ArticlesInner {\n");
    sb.append("    slug: ").append(toIndentedString(slug)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    tagList: ").append(toIndentedString(tagList)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    favorited: ").append(toIndentedString(favorited)).append("\n");
    sb.append("    favoritesCount: ").append(toIndentedString(favoritesCount)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
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

