package com.almis.ade.api.engine.jasper.generation.builder;

import com.almis.ade.api.bean.component.Paging;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 *
 * @author dfuentes
 */
public class PagingBuilder extends ElementBuilder<Paging, ComponentBuilder> {

  /**
   *
   * @param element
   * @param reportBuilder
   * @return
   */
  @Override
  public ComponentBuilder build(Paging element, JasperReportBuilder reportBuilder) {
    ComponentBuilder pagingBuilder;
    switch (element.getPagingType()){
      case X_SLASH_Y:
        pagingBuilder = cmp.pageXslashY();
        break;
      case X_OF_Y:
      default:
        pagingBuilder = cmp.pageXofY();
        break;
    }
    return pagingBuilder
            .setStyle(element.getStyle());
  }
}
