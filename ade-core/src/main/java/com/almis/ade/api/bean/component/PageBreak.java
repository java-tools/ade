package com.almis.ade.api.bean.component;

import net.sf.dynamicreports.report.constant.BreakType;

/**
 *
 * @author dfuentes
 */
public class PageBreak extends Element<PageBreak> {

  private BreakType pageBreakType;

  /**
   * PageBreak constructor
   * @param identifier page break identifier
   */
  public PageBreak(String identifier) {
    super(identifier);
    setPageBreakType(BreakType.PAGE);
  }

  /**
   * PageBreak constructor
   * @param identifier page break identifier
   * @param breakType page break type
   */
  public PageBreak(String identifier, BreakType breakType) {
    super(identifier);
    setPageBreakType(breakType);
  }

  /**
   * Get page break type
   *
   * @return BreakType
   */
  public BreakType getPageBreakType() {
    return pageBreakType;
  }

  /**
   * Set page break type
   *
   * @param pageBreakType page brake type
   *
   * @return PageBreak
   */
  public PageBreak setPageBreakType(BreakType pageBreakType) {
    this.pageBreakType = pageBreakType;
    return this;
  }
}
