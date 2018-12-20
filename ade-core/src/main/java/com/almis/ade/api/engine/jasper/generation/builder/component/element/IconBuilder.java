package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.Icon;
import com.almis.ade.api.util.IconUtil;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.StretchType;
import net.sf.jasperreports.renderers.Renderable;

import javax.validation.constraints.NotNull;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 *
 * @author dfuentes
 */
public class IconBuilder extends ElementBuilder<Icon, ComponentBuilder> {

  /**
   *
   * @param element
   * @param jasperReportBuilder
   * @return
   */
  @Override
  public ComponentBuilder build(@NotNull Icon element, JasperReportBuilder jasperReportBuilder) {
    return cmp.centerHorizontal(getIcon(element))
              .setMinWidth(1)
              .setStretchType(StretchType.CONTAINER_HEIGHT);
  }

  private ImageBuilder getIcon(Icon element) {
    ImageBuilder imageBuilder = null;
    Integer size = element.getSize();
    if (element.getExpression() != null) {
      imageBuilder = cmp.image(element.getExpression())
              .setFixedDimension(size, size)
              .setStretchType(StretchType.NO_STRETCH)
              .setHorizontalImageAlignment(HorizontalImageAlignment.CENTER);
    } else if (element.getIconName() != null) {
      String iconSvg = IconUtil.getIconFromFile(IconUtil.extractIcon(element.getIconName()));
      Renderable icon = IconUtil.renderIcon(iconSvg, element.getColor());
      imageBuilder = cmp.image(icon)
              .setFixedDimension(size, size)
              .setStretchType(StretchType.NO_STRETCH)
              .setHorizontalImageAlignment(HorizontalImageAlignment.CENTER);
    }

    return imageBuilder;
  }
}
