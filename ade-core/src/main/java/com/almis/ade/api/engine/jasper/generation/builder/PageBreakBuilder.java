package com.almis.ade.api.engine.jasper.generation.builder;

import com.almis.ade.api.bean.component.PageBreak;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 *
 * @author dfuentes
 */
public class PageBreakBuilder extends ElementBuilder<PageBreak, ComponentBuilder> {

  /**
   *
   * @param element
   * @param reportBuilder
   * @return
   */
  @Override
  public ComponentBuilder build(PageBreak element, JasperReportBuilder reportBuilder) {
    return cmp.pageBreak().setType(element.getPageBreakType());
  }
}
