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
   * Icon constructor
   * @param identifier icon identifier
   */
  public Icon(@NotNull String identifier) {
    super(identifier);
  }

  /**
   * Get icon name
   * @return icon name
   */
  public String getIconName() {
    return iconName;
  }

  /**
   * Set icon name
   * @param iconName icon name
   * @return Icon element
   */
  public Icon setIconName(String iconName) {
    this.iconName = iconName;
    return this;
  }

  /**
   * Get expression
   * @return AbstractSimpleExpression
   */
  public AbstractSimpleExpression<Renderable> getExpression() {
    return expression;
  }

  /**
   * Set expression
   * @param expression expression
   * @return Icon element
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
     * @return icon size
     */
    public int getSize() {
      return sizeValue;
    }
  }
}
