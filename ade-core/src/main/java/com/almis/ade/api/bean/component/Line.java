package com.almis.ade.api.bean.component;

import net.sf.dynamicreports.report.constant.LineStyle;

import javax.validation.constraints.NotNull;
import java.awt.*;

/**
 * Line element class
 * @author dfuentes
 */
public class Line extends Element<Line> {

  private Color lineColor = null;
  private Float lineWidth = null;
  private LineStyle lineStyle = null;

  /**
   * Line constructor
   * @param identifier line identifier
   */
  public Line(@NotNull String identifier) {
    super(identifier);
  }

  /**
   * Get line color
   * @return Color element
   */
  public Color getLineColor() {
    return lineColor;
  }

  /**
   * Set line color
   * @param lineColor line color
   * @return Line element
   */
  public Line setLineColor(Color lineColor) {
    this.lineColor = lineColor;
    return this;
  }

  /**
   * Get line width
   * @return line width
   */
  public Float getLineWidth() {
    return lineWidth;
  }

  /**
   * Set line width
   * @param lineWidth line width
   * @return Line element
   */
  public Line setLineWidth(Float lineWidth) {
    this.lineWidth = lineWidth;
    return this;
  }

  /**
   * Get line style
   * @return LineStyle
   */
  public LineStyle getLineStyle() {
    return lineStyle;
  }

  /**
   * Set line style
   * @param lineStyle line style
   * @return Line element
   */
  public Line setLineStyle(LineStyle lineStyle) {
    this.lineStyle = lineStyle;
    return this;
  }
}
