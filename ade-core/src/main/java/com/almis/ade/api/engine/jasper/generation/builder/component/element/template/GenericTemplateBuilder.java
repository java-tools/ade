package com.almis.ade.api.engine.jasper.generation.builder.component.element.template;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

/**
 *
 * @author dfuentes
 */
public class GenericTemplateBuilder extends ElementBuilder<Element, ComponentBuilder> {

  /**
   *
   */
  public GenericTemplateBuilder() {
    super();
  }

  /**
   *
   * @param element
   * @param reportBuilder
   * @return
   */
  @Override
  @SuppressWarnings("unchecked")
  public ComponentBuilder build(Element element, JasperReportBuilder reportBuilder) {
    if (element != null) {
      return (ComponentBuilder) getBuilderMapper()
              .getBuilder(element.getClass()
              ).build(element, reportBuilder);
    }
    return null;
  }
}
