package com.almis.ade.api.bean.component.grid;

import com.almis.ade.api.bean.component.Element;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dfuentes
 */
public class ReportHeader extends Element<ReportHeader> implements GridHeader {
  private String label;
  private List<ReportColumn> columnList;

  /**
   * Constructor
   *
   * @param identifier ReportHeader identifier
   */
  public ReportHeader(@NotNull String identifier) {
    super(identifier);
    columnList = new ArrayList<>();
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
  public ReportHeader setLabel(String label) {
    this.label = label;
    return this;
  }

  /**
   * Add column
   * @param column ReportColumn
   */
  public void addColumn(ReportColumn column){
    this.columnList.add(column);
  }

  /**
   * Get column list
   * @return ReportColumn list
   */
  public List<ReportColumn> getColumns() {
    return columnList;
  }
}
