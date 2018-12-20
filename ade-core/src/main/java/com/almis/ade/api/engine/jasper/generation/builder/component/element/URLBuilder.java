package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.URL;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;

import javax.validation.constraints.NotNull;
import java.awt.*;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 *
 * @author dfuentes
 */
public class URLBuilder extends ElementBuilder<URL, ComponentBuilder> {

  private StyleBuilder defaultHyperlinkStyle;

  @Override
  public void initialize() {
    defaultHyperlinkStyle = stl.style()
            .setForegroundColor(Color.blue);
  }

  /**
   *
   * @param element
   * @param jasperReportBuilder
   * @return
   */
  @Override
  public ComponentBuilder build(@NotNull URL element, JasperReportBuilder jasperReportBuilder) {
    java.net.URL url = element.getUrl();
    String urlString = url != null ? url.toString() : "";
    String label = element.getLabel() == null ? urlString : element.getLabel();

    if (url != null) {
      return cmp.text(label)
              .setStyle(defaultHyperlinkStyle.setParentStyle(element.getStyle()))
              .setHyperLink(hyperLink(url.toString()));
    }

    return null;
  }
}
