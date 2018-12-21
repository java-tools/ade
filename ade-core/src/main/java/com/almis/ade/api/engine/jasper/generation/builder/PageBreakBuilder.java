package com.almis.ade.api.engine.jasper.generation.builder;

import com.almis.ade.api.bean.component.PageBreak;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * PageBreakBuilder class
 * @author dfuentes
 */
public class PageBreakBuilder extends ElementBuilder<PageBreak, ComponentBuilder> {

  /**
   * PageBreak build
   * @param element PageBreak element
   * @param reportBuilder report builder
   * @return ComponentBuilder
   */
  @Override
  public ComponentBuilder build(PageBreak element, JasperReportBuilder reportBuilder) {
    return cmp.pageBreak().setType(element.getPageBreakType());
  }
}
