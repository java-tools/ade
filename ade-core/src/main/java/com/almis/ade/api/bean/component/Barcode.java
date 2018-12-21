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
   * @param identifier identifier
   * @param barcodeType barcode type
   */
  public Barcode(String identifier, BarcodeType barcodeType) {
    super(identifier);
    setBarcodeType(barcodeType);
  }

  /**
   * @param identifier identifier
   * @param barcodeType barcode type
   * @param code code
   */
  public Barcode(String identifier, BarcodeType barcodeType, String code) {
    super(identifier);
    setBarcodeType(barcodeType);
    setCode(code);
  }

  /**
   * Get Code
   *
   * @return code
   */
  public String getCode() {
    return code;
  }

  /**
   * Set code
   *
   * @param code code
   *
   * @return barcode
   */
  public Barcode setCode(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get size
   *
   * @return size
   */
  public int getSize() {
    return size == null ? Image.Size.SMALL.getSize() : size;
  }

  /**
   * Set size
   *
   * @param size size of barcode
   *
   * @return Barcode
   */
  public Barcode setSize(Image.Size size) {
    this.size = size.getSize();
    return this;
  }

  /**
   * Is the text shown
   *
   * @return true if text is shown
   */
  public boolean isDrawText() {
    return drawText;
  }

  /**
   * Set if the text is shown
   *
   * @param drawText text is shown flag
   *
   * @return Barcode
   */
  public Barcode setDrawText(boolean drawText) {
    this.drawText = drawText;
    return this;
  }

  /**
   * Get barcodeType
   *
   * @return Barcode type
   */
  public BarcodeType getBarcodeType() {
    return type;
  }

  /**
   * Set barcode type
   *
   * @param type barcode type
   *
   * @return Barcode
   */
  public Barcode setBarcodeType(BarcodeType type) {
    this.type = type;
    return this;
  }
}
