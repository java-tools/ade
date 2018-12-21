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
   * URL constructor
   * @param identifier URL identifier
   */
  public URL(@NotNull String identifier) {
    super(identifier);
  }

  /**
   * Get URL
   * @return URL
   */
  public java.net.URL getUrl() {
    return urlValue;
  }

  /**
   * Set URL
   * @param url url
   * @return URL
   */
  public URL setUrl(java.net.URL url) {
    this.urlValue = url;
    return this;
  }

  /**
   * Get label
   * @return label
   */
  public String getLabel() {
    return label;
  }

  /**
   * Set label
   * @param label label
   * @return URL
   */
  public URL setLabel(String label) {
    this.label = label;
    return this;
  }
}
