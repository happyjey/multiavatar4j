package com.sabazed.model;

import com.sabazed.model.map.SvgPartCollection;
import com.sabazed.model.map.ThemeCollection;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultSvgPart extends AbstractSvgPart {

  private static final String SVG_TEMPLATE = "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 231 231\">%s%s%s%s%s%s</svg>";
  private static final Pattern COLOR_PATTERN = Pattern.compile("#(.*?);");

  private final String defaultPart;
  private final ThemeType defaultTheme;
  private final boolean withoutEnv;

  private final ThemeType envType;
  private final ThemeType cloType;
  private final ThemeType headType;
  private final ThemeType mouthType;
  private final ThemeType eyesType;
  private final ThemeType topType;

  public ResultSvgPart(String hashString, String partType, ThemeType theme, boolean withoutEnv) {
    defaultPart = partType;
    defaultTheme = theme;
    this.withoutEnv = withoutEnv;

    var hash = getPartHash(hashString, 0, 2);
    setEnv(convertHash(hash));
    envType = convertTheme(hash);

    hash = getPartHash(hashString, 2, 4);
    setClo(convertHash(hash));
    cloType = convertTheme(hash);

    hash = getPartHash(hashString, 4, 6);
    setHead(convertHash(hash));
    headType = convertTheme(hash);

    hash = getPartHash(hashString, 6, 8);
    setMouth(convertHash(hash));
    mouthType = convertTheme(hash);

    hash = getPartHash(hashString, 8, 10);
    setEyes(convertHash(hash));
    eyesType = convertTheme(hash);

    hash = getPartHash(hashString, 10, 12);
    setTop(convertHash(hash));
    topType = convertTheme(hash);
  }

  public String buildSvg(ThemeCollection themes, SvgPartCollection svgParts) {
    return SVG_TEMPLATE.formatted(withoutEnv ? "" :
        getFinalPart(themes, svgParts, env[0], envType, ThemeSvgPart::getEnv, SvgPart::getEnv),
        getFinalPart(themes, svgParts, head[0], headType, ThemeSvgPart::getHead, SvgPart::getHead),
        getFinalPart(themes, svgParts, clo[0], cloType, ThemeSvgPart::getClo, SvgPart::getClo),
        getFinalPart(themes, svgParts, top[0], topType, ThemeSvgPart::getTop, SvgPart::getTop),
        getFinalPart(themes, svgParts, eyes[0], eyesType, ThemeSvgPart::getEyes, SvgPart::getEyes),
        getFinalPart(themes, svgParts, mouth[0], mouthType, ThemeSvgPart::getMouth, SvgPart::getMouth)
    );
  }

  private String getFinalPart(ThemeCollection themes, SvgPartCollection svgParts, String partType, ThemeType themeType,
                             Function<ThemeSvgPart, String[]> themeExtractor, Function<SvgPart, String> svgPartExtractor) {
    val colors = themeExtractor.apply(themes.get(partType).get(themeType));
    var svgString = svgPartExtractor.apply(svgParts.get(partType));
    val result = findAllMatch(svgString);
    for (int i = 0; i < result.size(); i++) {
      svgString = svgString.replaceFirst(result.get(i), colors[i] + ";");
    }
    return svgString;
  }

  private List<String> findAllMatch(String svgString) {
    Matcher matcher = COLOR_PATTERN.matcher(svgString);
    List<String> matches = new ArrayList<>();
    while (matcher.find()) {
      matches.add(matcher.group());
    }
    return matches;
  }

  private long getPartHash(String hash, int start, int end) {
    return Math.round(47.0 / 100 * Integer.parseInt(hash.substring(start, end)));
  }

  private String[] convertHash(long hash) {
    val result = defaultPart != null ? defaultPart : "%02d".formatted(hash % 16);
    return new String[] { result };
  }

  private ThemeType convertTheme(long hash) {
    return defaultTheme != null ? defaultTheme : ThemeType.fromHash(hash);
  }

}
