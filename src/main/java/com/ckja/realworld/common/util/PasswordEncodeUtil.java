package com.ckja.realworld.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class PasswordEncodeUtil {

  private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  private PasswordEncodeUtil() {
  }

  public static String encode(String rawPassword) {
    return PASSWORD_ENCODER.encode(rawPassword);
  }

  public static boolean matches(String rawPassword, String encodedPassword) {
    return PASSWORD_ENCODER.matches(rawPassword, encodedPassword);
  }
}
