package com.almis.ade.api.bean.component;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.jasperreports.renderers.Renderable;

import javax.validation.constraints.NotNull;
import java.awt.*;

/**
 *
 * @author dfuentes
 */
public class Icon extends Element<Icon> {
  private String iconName;
  private AbstractSimpleExpression<Renderable> expression;
  private Integer size;
  private Color color;

  /**
   *
   * @param identifier
   */
  public Icon(@NotNull String identifier) {
    super(identifier);
  }

  /**
   *
   * @return
   */
  public String getIconName() {
    return iconName;
  }

  /**
   *
   * @param iconName
   * @return
   */
  public Icon setIconName(String iconName) {
    this.iconName = iconName;
    return this;
  }

  /**
   *
   * @return
   */
  public AbstractSimpleExpression<Renderable> getExpression() {
    return expression;
  }

  /**
   *
   * @param expression
   * @return
   */
  public Icon setExpression(AbstractSimpleExpression<Renderable> expression) {
    this.expression = expression;
    return this;
  }

  /**
   * Get size
   * @return Size
   */
  public Integer getSize() {
    return size;
  }

  /**
   * Set size
   * @param size Size
   * @return this
   */
  public Icon setSize(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get icon color
   * @return icon color
   */
  public Color getColor() {
    return color;
  }

  /**
   * Set icon color
   * @param color Icon color
   * @return this
   */
  public Icon setColor(Color color) {
    this.color = color;
    return this;
  }

  /**
   * Image sizes
   */
  public enum Size {
    VERY_SMALL (10), SMALL (12), MEDIUM (14), BIG (16);

    private final int sizeValue;

    Size(int size) {
      this.sizeValue = size;
    }

    /**
     * Icon size
     * @return
     */
    public int getSize() {
      return sizeValue;
    }
  }
}
