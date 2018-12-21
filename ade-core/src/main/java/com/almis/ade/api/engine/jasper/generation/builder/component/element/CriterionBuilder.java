package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.Criterion;
import com.almis.ade.api.bean.component.Elements;
import com.almis.ade.api.bean.component.Text;
import com.almis.ade.api.bean.style.StyleTemplate;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.Markup;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

/**
 *
 * @author dfuentes
 */
public class CriterionBuilder extends ElementBuilder<Criterion, ComponentBuilder> {

  /**
   * Criterion component builder
   * @param element Criterion element
   * @param jasperReportBuilder report builder
   * @return ComponentBuilder
   */
  @Override
  @SuppressWarnings("unchecked")
  public ComponentBuilder build(@NotNull Criterion element, JasperReportBuilder jasperReportBuilder) {

    StyleBuilder style = stl.style(StyleTemplate.ROOT_STYLE).setMarkup(Markup.STYLED);
    Text criterionText = Elements.text(element.getIdentifier())
            .setValue("<b>" + element.getTitle() + "</b>: <i> " + element.getValue() + " </i>")
            .setStyle(style);

    return (ComponentBuilder) getBuilderMapper()
            .getBuilder(criterionText.getClass())
            .build(criterionText, jasperReportBuilder);
  }
}
