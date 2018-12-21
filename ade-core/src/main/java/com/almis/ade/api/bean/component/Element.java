package com.almis.ade.api.bean.component;

import com.almis.ade.api.interfaces.IBuilderInitializer;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

/**
 * Element class
 * @author dfuentes
 */
public class Element<T> implements IBuilderInitializer {

  protected String identifier;
  protected StyleBuilder style;

  /**
   *
   * @param identifier element identifier
   */
  public Element(@NotNull String identifier) {

    this.identifier = identifier;

    initialize();
  }

  /**
   *
   */
  @Override
  public void initialize() {
    style = stl.style();
  }

  /**
   * Get element identifier
   * @return element identifier
   */
  public String getIdentifier() {
    return identifier;
  }

  /**
   * Set element identifier
   * @param identifier identifier
   * @return Element object
   */
  @SuppressWarnings("unchecked")
  public T setIdentifier(String identifier) {
    this.identifier = identifier;
    return (T) this;
  }

  /**
   * Get style builder
   * @return StyleBuilder
   */
  public StyleBuilder getStyle() {
    return style;
  }

  /**
   * Set style builder
   * @param style style builder
   * @return Element object
   */
  @SuppressWarnings("unchecked")
  public T setStyle(StyleBuilder style) {
    this.style = style;
    return (T) this;
  }
}
