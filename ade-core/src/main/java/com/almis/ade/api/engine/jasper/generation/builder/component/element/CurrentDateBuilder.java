package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.CurrentDate;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 *
 * @author dfuentes
 */
public class CurrentDateBuilder extends ElementBuilder<CurrentDate, ComponentBuilder> {

  /**
   *
   * @param element
   * @param reportBuilder
   * @return
   */
  @Override
  public ComponentBuilder build(CurrentDate element, JasperReportBuilder reportBuilder) {
    return cmp.currentDate();
  }
}
