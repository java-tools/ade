package com.almis.ade.api.bean.component;

import net.sf.dynamicreports.report.constant.LineStyle;

import javax.validation.constraints.NotNull;
import java.awt.*;

/**
 *
 * @author dfuentes
 */
public class Line extends Element<Line> {

  private Color lineColor = null;
  private Float lineWidth = null;
  private LineStyle lineStyle = null;

  /**
   *
   * @param identifier
   */
  public Line(@NotNull String identifier) {
    super(identifier);
  }

  /**
   *
   * @return
   */
  public Color getLineColor() {
    return lineColor;
  }

  /**
   *
   * @param lineColor
   * @return
   */
  public Line setLineColor(Color lineColor) {
    this.lineColor = lineColor;
    return this;
  }

  /**
   *
   * @return
   */
  public Float getLineWidth() {
    return lineWidth;
  }

  /**
   *
   * @param lineWidth
   * @return
   */
  public Line setLineWidth(Float lineWidth) {
    this.lineWidth = lineWidth;
    return this;
  }

  /**
   *
   * @return
   */
  public LineStyle getLineStyle() {
    return lineStyle;
  }

  /**
   *
   * @param lineStyle
   * @return
   */
  public Line setLineStyle(LineStyle lineStyle) {
    this.lineStyle = lineStyle;
    return this;
  }
}
