package com.sabazed.model;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class SvgPart extends AbstractSvgPart {

  private static final String ENV = "<path d=\"M33.83,33.83a115.5,115.5,0 1,1,0,163.34,115.49,115.49,0 0,1,0-163.34Z\" style=\"fill:#01;\"/>";
  private static final String HEAD = "<path d=\"m115.5 51.75a63.75 63.75 0 0 0-10.5 126.63v14.09a115.5 115.5 0 0 0-53.729 19.027 115.5 115.5 0 0 0 128.46 0 115.5 115.5 0 0 0-53.729-19.029v-14.084a63.75 63.75 0 0 0 53.25-62.881 63.75 63.75 0 0 0-63.75-63.75z\" style=\"fill:#01;\"/>";
  private static final String STR = "stroke-linecap:round;stroke-linejoin:round;stroke-width:";

  public String getEnv() {
    return ENV;
  }

  public String getClo() {
    return replaceStroke(clo[0]);
  }

  public String getHead() {
    return HEAD;
  }

  public String getMouth() {
    return replaceStroke(mouth[0]);
  }

  public String getEyes() {
    return replaceStroke(eyes[0]);
  }

  public String getTop() {
    return replaceStroke(top[0]);
  }

  private static String replaceStroke(String field) {
    return field.replaceAll("\\$str", STR);
  }

}
