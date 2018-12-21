package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.CurrentDate;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * CurrentDate builder class
 * @author dfuentes
 */
public class CurrentDateBuilder extends ElementBuilder<CurrentDate, ComponentBuilder> {

  /**
   *  CurrentDate builder
   *
   * @param element CurrentDate element
   * @param reportBuilder report builder
   * @return ComponentBuilder
   */
  @Override
  public ComponentBuilder build(CurrentDate element, JasperReportBuilder reportBuilder) {
    return cmp.currentDate();
  }
}
