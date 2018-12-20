package com.almis.ade.api.bean.component;

import com.almis.ade.api.enumerate.BarcodeType;

/**
 * @author dfuentes
 */
public class Barcode extends Element<Barcode> {

  private String code;
  private Integer size;
  private Boolean drawText;
  private BarcodeType type;

  /**
   * @param identifier
   */
  public Barcode(String identifier, BarcodeType barcodeType) {
    super(identifier);
    setBarcodeType(barcodeType);
  }

  /**
   * @param identifier
   * @param code
   */
  public Barcode(String identifier, BarcodeType barcodeType, String code) {
    super(identifier);
    setBarcodeType(barcodeType);
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
  public Barcode setCode(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get size
   *
   * @return
   */
  public int getSize() {
    return size == null ? Image.Size.SMALL.getSize() : size;
  }

  /**
   * Set size
   *
   * @param size
   *
   * @return
   */
  public Barcode setSize(Image.Size size) {
    this.size = size.getSize();
    return this;
  }

  /**
   * Is the text shown
   *
   * @return
   */
  public boolean isDrawText() {
    return drawText;
  }

  /**
   * Set if the text is shown
   *
   * @param drawText
   *
   * @return
   */
  public Barcode setDrawText(boolean drawText) {
    this.drawText = drawText;
    return this;
  }

  /**
   * Get barcodeType
   *
   * @return
   */
  public BarcodeType getBarcodeType() {
    return type;
  }

  /**
   * Set barcode type
   *
   * @param type
   *
   * @return
   */
  public Barcode setBarcodeType(BarcodeType type) {
    this.type = type;
    return this;
  }
}
