package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.Image;
import com.almis.ade.api.util.ImageUtil;
import lombok.extern.log4j.Log4j2;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.jasperreports.renderers.Renderable;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 * @author dfuentes
 */
@Log4j2
public class ImageBuilder extends ElementBuilder<Image, ComponentBuilder> {

  /**
   * Image builder
   *
   * @param element             image element
   * @param jasperReportBuilder report builder
   * @return ComponentBuilder
   */
  @Override
  public ComponentBuilder build(@NotNull Image element, JasperReportBuilder jasperReportBuilder) {

    // Image as input stream
    try {
      InputStream imageAsInputStream = ImageUtil.getImageAsInputStream(element);
      if (imageAsInputStream != null) {
        return cmp.image(imageAsInputStream)
          .setHorizontalImageAlignment(HorizontalImageAlignment.CENTER)
          .setWidth(element.getSize())
          .setHeight(Math.round(element.getSize() * element.getScale()));
      } else if (element.getSVGImage() != null) {
        // Image as renderable
        Renderable imageAsRenderable = ImageUtil.getImageAsRenderable(element);
        if (imageAsRenderable != null) {
          return cmp.image(imageAsRenderable)
            .setHorizontalImageAlignment(HorizontalImageAlignment.CENTER)
            .setFixedDimension(element.getSize(), Math.round(element.getSize() * element.getScale()))
            .setImageScale(ImageScale.RETAIN_SHAPE);
        }
      } else if (element.getExpression() != null) {
        return cmp.image(element.getExpression())
          .setHorizontalImageAlignment(HorizontalImageAlignment.CENTER)
          .setFixedDimension(element.getSize(), Math.round(element.getSize() * element.getScale()))
          .setImageScale(ImageScale.RETAIN_SHAPE);
      }
    } catch (IOException exc) {
      log.warn("Error parsing url to image", exc);
    }
    return null;
  }
}
