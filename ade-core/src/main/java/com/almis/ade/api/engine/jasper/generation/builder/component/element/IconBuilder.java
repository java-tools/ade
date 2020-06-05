package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.Icon;
import com.almis.ade.api.util.IconUtil;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.jasperreports.renderers.Renderable;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * IconBuilder class
 *
 * @author dfuentes
 */
public class IconBuilder extends ElementBuilder<Icon, ComponentBuilder> {

  /**
   * Icon builder
   *
   * @param element             Icon element
   * @param jasperReportBuilder report builder
   * @return ComponentBuilder
   */
  @Override
  public ComponentBuilder build(@NotNull Icon element, JasperReportBuilder jasperReportBuilder) {
    Integer size = element.getSize();
    if (element.getExpression() != null) {
      return cmp.image(element.getExpression())
        .setFixedDimension(size, size)
        .setImageScale(ImageScale.RETAIN_SHAPE)
        .setHorizontalImageAlignment(HorizontalImageAlignment.CENTER);
    } else if (element.getIconName() != null) {
      String iconSvg = IconUtil.getIconFromFile(IconUtil.extractIcon(element.getIconName()));
      Renderable icon = IconUtil.renderIcon(iconSvg, element.getColor());
      return cmp.image(icon)
        .setFixedDimension(size, size)
        .setImageScale(ImageScale.RETAIN_SHAPE)
        .setHorizontalImageAlignment(HorizontalImageAlignment.CENTER);
    }
    return null;
  }
}
