package com.almis.ade.api.engine.jasper.generation.builder.component.element.specific;

import com.almis.ade.api.bean.section.PageHeader;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * PageHeaderBuilder class
 * @author dfuentes
 */
public class PageHeaderBuilder extends ElementBuilder<PageHeader, ComponentBuilder> {

  /**
   * PageHeader build
   * @param element PageHeader element
   * @param jasperReportBuilder report builder
   * @return ComponentBuilder
   */
  @Override
  @SuppressWarnings("unchecked")
  public ComponentBuilder build(@NotNull PageHeader element, JasperReportBuilder jasperReportBuilder) {

    VerticalListBuilder verticalListBuilder = cmp.verticalList().setGap(2);
    HorizontalListBuilder horizontalListBuilder = cmp.horizontalFlowList();
    verticalListBuilder.add(horizontalListBuilder);

    if (element.getLogo() != null) {
      ComponentBuilder logoBuilder = (ComponentBuilder) getBuilderMapper()
              .getBuilder(element.getLogo().getClass())
              .build(element.getLogo(), jasperReportBuilder);
      horizontalListBuilder.add(logoBuilder);
    }

    if (element.getTitle() != null) {
      ComponentBuilder titleBuilder = (ComponentBuilder) getBuilderMapper()
              .getBuilder(element.getTitle().getClass())
              .build(element.getTitle(), jasperReportBuilder);
      horizontalListBuilder.add(titleBuilder);
    }

    if (element.getLine() != null) {
      ComponentBuilder lineBuilder = (ComponentBuilder) getBuilderMapper()
              .getBuilder(element.getLine().getClass())
              .build(element.getLine(), jasperReportBuilder);
      verticalListBuilder.add(lineBuilder);
    }

    return verticalListBuilder;
  }
}
