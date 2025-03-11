package com.sabazed;

import com.sabazed.model.ResultSvgPart;
import com.sabazed.model.ThemeType;
import com.sabazed.model.map.SvgPartCollection;
import com.sabazed.model.map.ThemeCollection;
import com.sabazed.utils.HelperUtils;
import com.sabazed.utils.exception.ResourceLoadingException;
import lombok.val;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AvatarGenerator {

  private final MessageDigest sha256;
  private final ThemeCollection themes;
  private final SvgPartCollection svgParts;

  public AvatarGenerator() throws ResourceLoadingException, NoSuchAlgorithmException {
    this.sha256 = MessageDigest.getInstance("SHA-256");;
    this.themes = HelperUtils.loadResource("/themes.json", ThemeCollection.class);
    this.svgParts = HelperUtils.loadResource("/svg-parts.json", SvgPartCollection.class);
  }

  public String generate(String seed, String partType, ThemeType themeType, boolean withoutEnv) {
    if (seed.isEmpty()) {
      return "";
    }
    // Calculate SHA-256 hash of the seed
    val seedDigest = sha256.digest(seed.getBytes(StandardCharsets.UTF_8));
    val sha256Hash = "%064x".formatted(new BigInteger(1, seedDigest));
    val sha256Numbers = sha256Hash.replaceAll("\\D", "");
    val hash = sha256Numbers.substring(0, 12);
    // Generate SVG
    ResultSvgPart svgPart = new ResultSvgPart(hash, partType, themeType, withoutEnv);
    return svgPart.buildSvg(themes, svgParts);
  }

}
