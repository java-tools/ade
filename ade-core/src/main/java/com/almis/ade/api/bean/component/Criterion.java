package com.almis.ade.api.bean.component;

import com.almis.ade.api.bean.style.StyleTemplate;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

/**
 *
 * @author dfuentes
 */
public class Criterion extends Element<Criterion> {

  private String title;
  private String value;

  /**
   * Criterion element constructor
   * @param identifier Criterion id
   */
  public Criterion(@NotNull String identifier) {
    super(identifier);
    setStyle(stl.style(StyleTemplate.ROOT_STYLE));
  }

  /**
   * Get criterion title
   * @return criterion title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Set criterion title
   * @param title criterion title
   * @return Criterion element
   */
  public Criterion setTitle(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get criterion value
   * @return criterion value
   */
  public String getValue() {
    return value;
  }

  /**
   * Set criterion value
   * @param value criterion value
   * @return Criterion element
   */
  public Criterion setValue(String value) {
    this.value = value;
    return this;
  }
}
