package com.sabazed.model;

public enum ThemeType {
  A, B, C;

  public static ThemeType fromHash(double hash) {
    return ThemeType.valueOf(String.valueOf((char) ('A' + hash / 16)));
  }
}
