package com.almis.ade.api.bean.component;

import javax.validation.constraints.NotNull;

/**
 *
 * @author dfuentes
 */
public class Text extends Element<Text> {

  private String value;

  /**
   *
   * @param identifier
   */
  public Text(@NotNull String identifier) {
    super(identifier);
  }

  /**
   * Text value
   * @return text
   */
  public String getValue() {
    return value;
  }

  /**
   * Text value
   * @param value text
   * @return this
   */
  public Text setValue(String value) {
    this.value = value;
    return this;
  }
}
