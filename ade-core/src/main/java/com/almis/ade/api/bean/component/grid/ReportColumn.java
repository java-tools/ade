package com.almis.ade.api.bean.component.grid;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.bean.style.StyleTemplate;
import com.almis.ade.api.enumerate.ColumnType;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

/**
 *
 * @author dfuentes
 */
public class ReportColumn extends Element<ReportColumn> implements GridHeader {
  private String label;
  private String field;
  private ColumnType type;
  private Integer width;
  private HorizontalTextAlignment align;
  private Integer fontSize;

  /**
   * Constructor
   *
   * @param identifier report column identifier
   */
  public ReportColumn(@NotNull String identifier) {
    super(identifier);
    setStyle(stl.style(StyleTemplate.COLUMN_DATA_STYLE));
  }

  /**
   * Get column label
   * @return ReportColumn label
   */
  public String getLabel() {
    return label;
  }

  /**
   * Set column label
   * @param label ReportColumn label
   * @return this
   */
  public ReportColumn setLabel(String label) {
    this.label = label;
    return this;
  }

  /**
   * Get column field
   * @return ReportColumn field
   */
  public String getField() {
    return field;
  }

  /**
   * Set column field
   * @param field Field
   * @return this
   */
  public ReportColumn setField(String field) {
    this.field = field;
    return this;
  }

  /**
   * Get column type
   * @return column type
   */
  public ColumnType getType() {
    return type;
  }

  /**
   * Set column type
   * @param type column type
   * @return this
   */
  public ReportColumn setType(ColumnType type) {
    this.type = type;
    return this;
  }

  /**
   * Get relative width
   * @return width
   */
  public Integer getWidth() {
    return width;
  }

  /**
   * Set relative width
   * @param width Width
   * @return this
   */
  public ReportColumn setWidth(Integer width) {
    this.width = width;
    return this;
  }

  /**
   * Get text align
   * @return Align
   */
  public HorizontalTextAlignment getAlign() {
    return align;
  }

  /**
   * Set text align
   * @param align Align
   * @return this
   */
  public ReportColumn setAlign(HorizontalTextAlignment align) {
    this.align = align;
    return this;
  }

  /**
   * Get font size
   * @return Font size
   */
  public Integer getFontSize() {
    return fontSize;
  }

  /**
   * Set font size
   * @param fontSize Font size
   * @return this
   */
  public ReportColumn setFontSize(Integer fontSize) {
    this.fontSize = fontSize;
    return this;
  }
}
