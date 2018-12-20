package com.almis.ade.api.engine.jasper.generation.builder.component.element.grid;

import com.almis.ade.api.bean.component.grid.ReportHeader;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.grid;

/**
 *
 * @author dfuentes
 */
public class HeaderBuilder extends ElementBuilder<ReportHeader, ColumnTitleGroupBuilder> {

  /**
   *
   * @param element
   * @param reportBuilder
   * @return
   */
  @Override
  public ColumnTitleGroupBuilder build(@NotNull ReportHeader element, JasperReportBuilder reportBuilder) {
    return grid.titleGroup(element.getLabel());
  }
}
