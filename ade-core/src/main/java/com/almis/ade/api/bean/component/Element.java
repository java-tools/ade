package com.almis.ade.api.bean.component;

import com.almis.ade.api.interfaces.IBuilderInitializer;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

/**
 *
 * @author dfuentes
 * @param <T>
 */
public class Element<T> implements IBuilderInitializer {

  /**
   *
   */
  protected String identifier;

  /**
   *
   */
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
   *
   * @return
   */
  public String getIdentifier() {
    return identifier;
  }

  /**
   *
   * @param identifier
   * @return
   */
  @SuppressWarnings("unchecked")
  public T setIdentifier(String identifier) {
    this.identifier = identifier;
    return (T) this;
  }

  /**
   *
   * @return
   */
  public StyleBuilder getStyle() {
    return style;
  }

  /**
   *
   * @param style
   * @return
   */
  @SuppressWarnings("unchecked")
  public T setStyle(StyleBuilder style) {
    this.style = style;
    return (T) this;
  }
}
