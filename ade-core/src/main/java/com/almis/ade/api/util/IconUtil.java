package com.almis.ade.api.util;

import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.renderers.Renderable;
import org.apache.commons.io.IOUtils;
import org.springframework.core.env.Environment;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Icon utilities
 */
@Log4j2
public class IconUtil {

  // Environment
  private static String defaultIconPath;
  private static String defaultIconExtension;

  /**
   * Hide the constructor
   */
  private IconUtil() {
  }

  /**
   * Initialize Utility class
   *
   * @param springEnvironment Spring environment
   */
  public static void init(Environment springEnvironment) {
    Environment environment = springEnvironment;
    defaultIconPath = environment.getProperty("ade.icon.path", "fonts/fontawesome/");
    defaultIconExtension = environment.getProperty("ade.icon.extension", ".svg");
  }

  /**
   * Extract icon from iconValue
   *
   * @param iconValue Icon value
   * @return Icon extracted
   */
  public static String extractIcon(String iconValue) {
    String icon = iconValue;

    // Remove other words
    icon = icon.split(" ")[0];

    // Remove fa-
    icon = icon.replace("fa-", "");

    return icon;
  }

  /**
   * Extract color from iconStyle
   *
   * @param iconStyle Icon style
   * @return extracted color
   */
  public static Color extractColor(String iconStyle) {
    Color color = null;
    if (iconStyle != null) {
      if (iconStyle.contains("danger")) {
        color = new Color(225, 68, 48, 255);
      } else if (iconStyle.contains("warning")) {
        color = new Color(242, 159, 41, 255);
      } else if (iconStyle.contains("info")) {
        color = new Color(57, 179, 215, 255);
      } else if (iconStyle.contains("success")) {
        color = new Color(70, 172, 70, 255);
      } else if (iconStyle.contains("color(")) {
        color = getColor(iconStyle);
      }
    }
    return color;
  }

  /**
   * Extract color from iconStyle
   *
   * @param iconStyle Icon style
   * @return extracted color
   */
  public static Color getColor(String iconStyle) {
    // Get color values
    Color color = null;
    Pattern pattern = Pattern.compile("color\\((\\d+\\,\\d+\\,\\d+\\,\\d+)\\)");
    Matcher matcher = pattern.matcher(iconStyle);
    if (matcher.find()) {
      String[] colorList = matcher.group(1).split(",");
      color = new Color(Integer.parseInt(colorList[0]),
        Integer.parseInt(colorList[1]),
        Integer.parseInt(colorList[2]),
        Integer.parseInt(colorList[3]));
    }
    return color;
  }

  /**
   * Add color to icon
   *
   * @param icon  Icon as string
   * @param color Color to add
   * @return icon with color
   */
  private static String addColor(String icon, Color color) {
    StringBuilder builder = new StringBuilder();
    builder.append(" fill=\"#")
      .append(Integer.toHexString(color.getRed()))
      .append(Integer.toHexString(color.getGreen()))
      .append(Integer.toHexString(color.getBlue())).append("\" fill-opacity=\"")
      .append(color.getAlpha() / 255f).append("\"></path>");
    return icon.replace("></path>", builder.toString());
  }

  /**
   * Render icon to svg
   *
   * @param icon  string icon
   * @param color color element
   * @return Renderable
   */
  public static Renderable renderIcon(String icon, Color color) {
    // Add color to icon
    if (color != null) {
      icon = addColor(icon, color);
    }

    // Render icon
    return ImageUtil.getSvgImageAsRenderable(icon);
  }

  /**
   * Get icon from file
   *
   * @param iconName icon name
   * @return Icon as string
   */
  public static String getIconFromFile(String iconName) {
    String icon = null;
    String iconPath = defaultIconPath + iconName + defaultIconExtension;
    try (InputStream inputStream = FileUtil.getResourceAsStream(iconPath)) {
      if (inputStream != null) {
        icon = new String(IOUtils.toByteArray(inputStream), StandardCharsets.UTF_8);
      }
    } catch (IOException exc) {
      log.warn("Error retrieving icon file: {}", iconPath, exc);
    }
    return icon;
  }
}
