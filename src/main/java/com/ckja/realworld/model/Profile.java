package com.ckja.realworld.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Profile
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-01T21:06:09.127675+09:00[Asia/Tokyo]", comments = "Generator version: 7.20.0")
public class Profile {

  private String username;

  private JsonNullable<String> bio = JsonNullable.<String>undefined();

  private JsonNullable<String> image = JsonNullable.<String>undefined();

  private Boolean following;

  public Profile() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Profile(String username, String bio, String image, Boolean following) {
    this.username = username;
    this.bio = JsonNullable.of(bio);
    this.image = JsonNullable.of(image);
    this.following = following;
  }

  public Profile username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   */
  @NotNull 
  @Schema(name = "username", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Profile bio(String bio) {
    this.bio = JsonNullable.of(bio);
    return this;
  }

  /**
   * Get bio
   * @return bio
   */
  @NotNull 
  @Schema(name = "bio", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("bio")
  public JsonNullable<String> getBio() {
    return bio;
  }

  public void setBio(JsonNullable<String> bio) {
    this.bio = bio;
  }

  public Profile image(String image) {
    this.image = JsonNullable.of(image);
    return this;
  }

  /**
   * Get image
   * @return image
   */
  @NotNull 
  @Schema(name = "image", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("image")
  public JsonNullable<String> getImage() {
    return image;
  }

  public void setImage(JsonNullable<String> image) {
    this.image = image;
  }

  public Profile following(Boolean following) {
    this.following = following;
    return this;
  }

  /**
   * Get following
   * @return following
   */
  @NotNull 
  @Schema(name = "following", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("following")
  public Boolean getFollowing() {
    return following;
  }

  public void setFollowing(Boolean following) {
    this.following = following;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profile profile = (Profile) o;
    return Objects.equals(this.username, profile.username) &&
        Objects.equals(this.bio, profile.bio) &&
        Objects.equals(this.image, profile.image) &&
        Objects.equals(this.following, profile.following);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, bio, image, following);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Profile {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    bio: ").append(toIndentedString(bio)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    following: ").append(toIndentedString(following)).append("\n");
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

