package com.almis.ade.api.bean.component;

/**
 * @author dfuentes
 */
public class QRCode extends Element<QRCode> {

  private String code;

  /**
   * @param identifier
   */
  public QRCode(String identifier) {
    super(identifier);
  }

  /**
   * @param identifier
   */
  public QRCode(String identifier, String code) {
    super(identifier);
    setCode(code);
  }


  /**
   * Get Code
   *
   * @return
   */
  public String getCode() {
    return code;
  }

  /**
   * Set code
   *
   * @param code
   *
   * @return
   */
  public QRCode setCode(String code) {
    this.code = code;
    return this;
  }
}
