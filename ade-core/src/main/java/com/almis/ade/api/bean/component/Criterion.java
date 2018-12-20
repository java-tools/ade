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
   *
   * @param identifier
   */
  public Criterion(@NotNull String identifier) {
    super(identifier);
    setStyle(stl.style(StyleTemplate.ROOT_STYLE));
  }

  /**
   *
   * @return
   */
  public String getTitle() {
    return title;
  }

  /**
   *
   * @param title
   * @return
   */
  public Criterion setTitle(String title) {
    this.title = title;
    return this;
  }

  /**
   *
   * @return
   */
  public String getValue() {
    return value;
  }

  /**
   *
   * @param value
   * @return
   */
  public Criterion setValue(String value) {
    this.value = value;
    return this;
  }
}
