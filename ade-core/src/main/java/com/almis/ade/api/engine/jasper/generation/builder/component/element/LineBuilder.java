package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.Line;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.style.PenBuilder;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

/**
 *
 * @author dfuentes
 */
public class LineBuilder extends ElementBuilder<Line, ComponentBuilder> {

  /**
   *
   * @param element
   * @param jasperReportBuilder
   * @return
   */
  @Override
  public ComponentBuilder build(@NotNull Line element, JasperReportBuilder jasperReportBuilder) {
    net.sf.dynamicreports.report.builder.component.LineBuilder lineBuilder = cmp.line();
    PenBuilder penBuilder = stl.pen();
    if (element.getLineColor() != null) {
      penBuilder.setLineColor(element.getLineColor());
    }

    if (element.getLineStyle() != null) {
      penBuilder.setLineStyle(element.getLineStyle());
    }

    if (element.getLineWidth() != null) {
      penBuilder.setLineWidth(element.getLineWidth());
    }
    lineBuilder.setPen(penBuilder);
    return lineBuilder;
  }
}
