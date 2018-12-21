package com.almis.ade.api.bean.component;

/**
 * QRCode class
 *
 * @author dfuentes
 */
public class QRCode extends Element<QRCode> {

  private String code;

  /**
   * QRCode constructor
   * @param identifier QRCode identifier
   */
  public QRCode(String identifier) {
    super(identifier);
  }

  /**
   *  QRCode constructor
   * @param identifier QRCode identifier
   * @param code QRCode identifier
   */
  public QRCode(String identifier, String code) {
    super(identifier);
    setCode(code);
  }


  /**
   * Get Code
   *
   * @return string code
   */
  public String getCode() {
    return code;
  }

  /**
   * Set code
   *
   * @param code code
   *
   * @return QRCode
   */
  public QRCode setCode(String code) {
    this.code = code;
    return this;
  }
}
