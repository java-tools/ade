package com.almis.ade.api.bean.component;

import javax.validation.constraints.NotNull;

/**
 *
 * @author dfuentes
 */
public class URL extends Element<URL> {
  private java.net.URL urlValue;
  private String label;

  /**
   *
   * @param identifier
   */
  public URL(@NotNull String identifier) {
    super(identifier);
  }

  /**
   *
   * @return
   */
  public java.net.URL getUrl() {
    return urlValue;
  }

  /**
   *
   * @param url
   * @return
   */
  public URL setUrl(java.net.URL url) {
    this.urlValue = url;
    return this;
  }

  /**
   *
   * @return
   */
  public String getLabel() {
    return label;
  }

  /**
   *
   * @param label
   * @return
   */
  public URL setLabel(String label) {
    this.label = label;
    return this;
  }
}
