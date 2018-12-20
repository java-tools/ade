package com.almis.ade.api.bean.component;

import net.sf.dynamicreports.report.constant.BreakType;

/**
 *
 * @author dfuentes
 */
public class PageBreak extends Element<PageBreak> {

  private BreakType pageBreakType;

  /**
   *
   * @param identifier
   */
  public PageBreak(String identifier) {
    super(identifier);
    setPageBreakType(BreakType.PAGE);
  }

  /**
   *
   * @param identifier
   * @param breakType
   */
  public PageBreak(String identifier, BreakType breakType) {
    super(identifier);
    setPageBreakType(breakType);
  }

  /**
   * Get page break type
   *
   * @return
   */
  public BreakType getPageBreakType() {
    return pageBreakType;
  }

  /**
   * Set page break type
   *
   * @param pageBreakType
   *
   * @return
   */
  public PageBreak setPageBreakType(BreakType pageBreakType) {
    this.pageBreakType = pageBreakType;
    return this;
  }
}
