package com.sabazed;

import com.sabazed.model.ThemeType;
import com.sabazed.utils.exception.ResourceLoadingException;
import lombok.val;
import org.apache.batik.transcoder.TranscoderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.sabazed.utils.HelperUtils.writeResourceContent;

class AvatarGeneratorTest {

  private AvatarGenerator avatarGenerator;

  @BeforeEach
  void setUp() throws ResourceLoadingException, NoSuchAlgorithmException {
    avatarGenerator = new AvatarGenerator();
  }

  @Test
  void buildIndex() throws IOException {
    StringBuilder html = new StringBuilder("<!doctype html><html> <head> <meta charset=\"utf-8\"> <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"> <title>Multiavatar - All 48 Initial Avatar Designs</title> <style>body, html{width:100%; height:100%;}body{background-color: #fff; overflow-x: hidden; padding:0px; margin:0px;}*{box-sizing: border-box;}.container{width: 100%; height: 100%; padding: 20px;}.avatar{width: 110px; height:110px; float:left; margin: 10px;}</style> </head> <body> <div id=\"container\" class=\"container\">");
    for (int i = 0; i < 16; i++) {
      for (var themeType : List.of(ThemeType.A, ThemeType.B, ThemeType.C)) {
        html.append("<div class=\"avatar\">")
            .append(avatarGenerator.generate("Starcrasher", "%02d".formatted(i), themeType, false))
            .append("</div>");
      }
    }
    html.append("</div><div style=\"height:40px;clear:both;\"></div></body></html>");
    writeResourceContent("index.html", html.toString());
  }

  @Test
  void buildOther() throws IOException {
    val html = "<html>" +
        avatarGenerator.generate("Starcrasher", null, null, false) + 
        "<br>" +
        avatarGenerator.generate("Pandalion", null, null, true) +
        "<br>" +
        avatarGenerator.generate("Starcrasher", "11", ThemeType.C, false) +
        "<br>" +
        avatarGenerator.generate("Starcrasher","08", ThemeType.C, false) +
        "<br>" +
        avatarGenerator.generate("Starcrasher", "15", ThemeType.B, false) +
        "<br>" +
        avatarGenerator.generate("123456789", null, null, false)
        + "</html>";
    writeResourceContent("other.html",html);
  }

  @Test
  void testGeneratePngAvatars() throws IOException, TranscoderException {
    for (int i = 0; i < 16; i++) {
      for (var themeType : List.of(ThemeType.A, ThemeType.B, ThemeType.C)) {
        val png = avatarGenerator.generatePng("Starcrasher", "%02d".formatted(i), themeType, false);
        writeResourceContent("/avatar-%02d-%s.png".formatted(i, themeType), png);
      }
    }
  }

  @Test
  void testGeneratePngAvatars1() throws IOException, TranscoderException {
    val i = 15;
    val themeType = ThemeType.B;
    val png = avatarGenerator.generatePng("Starcrasher", "%02d".formatted(i), themeType, false);
    writeResourceContent("/avatar-%02d-%s.png".formatted(i, themeType), png);


  }

}