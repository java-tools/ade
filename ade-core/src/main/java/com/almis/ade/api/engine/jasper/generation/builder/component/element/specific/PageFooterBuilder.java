package com.almis.ade.api.engine.jasper.generation.builder.component.element.specific;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.bean.section.PageFooter;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 *
 * @author dfuentes
 */
public class PageFooterBuilder extends ElementBuilder<PageFooter, ComponentBuilder> {

  /**
   *
   * @param element
   * @param jasperReportBuilder
   * @return
   */
  @Override
  @SuppressWarnings("unchecked")
  public ComponentBuilder build(@NotNull PageFooter element, JasperReportBuilder jasperReportBuilder) {

    VerticalListBuilder verticalListBuilder = cmp.verticalList().setGap(2);
    HorizontalListBuilder horizontalListBuilder = cmp.horizontalFlowList();

    // Add line on top
    if (element.getLine() != null) {
      ComponentBuilder lineBuilder = (ComponentBuilder) getBuilderMapper()
              .getBuilder(element.getLine().getClass())
              .build(element.getLine(), jasperReportBuilder);
      verticalListBuilder.add(lineBuilder);
    }

    // Add horizontal list builder
    verticalListBuilder.add(horizontalListBuilder);
    if (element.getVariables() != null) {
      HorizontalListBuilder variableListBuilder = cmp.horizontalFlowList();
      for (Element variable : element.getVariables()) {
        ComponentBuilder variableBuilder = (ComponentBuilder) getBuilderMapper()
                .getBuilder(variable.getClass())
                .build(variable, jasperReportBuilder);
        variableListBuilder.add(variableBuilder);
      }
      horizontalListBuilder.add(variableListBuilder);
    }

    // Add paging on right of the page
    if (element.getPaging() != null) {
      ComponentBuilder pagingBuilder = (ComponentBuilder) getBuilderMapper()
              .getBuilder(element.getPaging().getClass())
              .build(element.getPaging(), jasperReportBuilder);
      horizontalListBuilder
              .add(pagingBuilder);
    }

    return verticalListBuilder;
  }
}
