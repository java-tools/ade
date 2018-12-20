package com.almis.ade.api.bean.component.grid;

/**
 *
 * @author dfuentes
 */
public interface GridHeader {
  /**
   * Get column label
   * @return ReportColumn label
   */
  String getLabel();

  /**
   * Set column label
   * @param label ReportColumn label
   * @return this
   */
  GridHeader setLabel(String label);
}
