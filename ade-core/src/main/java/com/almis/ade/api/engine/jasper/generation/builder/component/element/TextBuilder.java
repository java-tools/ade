package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.Text;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 *
 * @author dfuentes
 */
public class TextBuilder extends ElementBuilder<Text, TextFieldBuilder<String>> {

  /**
   *
   * @param element
   * @param jasperReportBuilder
   * @return
   */
  @Override
  public TextFieldBuilder<String> build(@NotNull Text element, JasperReportBuilder jasperReportBuilder) {
    return cmp.text(element.getValue())
            .setStyle(element.getStyle());
  }
}
