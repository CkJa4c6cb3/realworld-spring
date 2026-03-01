package com.ckja.realworld.model;

import java.net.URI;
import java.util.Objects;
import com.ckja.realworld.model.NewComment;
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
 * CreateArticleCommentRequest
 */

@JsonTypeName("CreateArticleComment_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-01T21:06:09.127675+09:00[Asia/Tokyo]", comments = "Generator version: 7.20.0")
public class CreateArticleCommentRequest {

  private NewComment comment;

  public CreateArticleCommentRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateArticleCommentRequest(NewComment comment) {
    this.comment = comment;
  }

  public CreateArticleCommentRequest comment(NewComment comment) {
    this.comment = comment;
    return this;
  }

  /**
   * Get comment
   * @return comment
   */
  @NotNull @Valid 
  @Schema(name = "comment", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("comment")
  public NewComment getComment() {
    return comment;
  }

  public void setComment(NewComment comment) {
    this.comment = comment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateArticleCommentRequest createArticleCommentRequest = (CreateArticleCommentRequest) o;
    return Objects.equals(this.comment, createArticleCommentRequest.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(comment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateArticleCommentRequest {\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
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

