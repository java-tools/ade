package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.Image;
import com.almis.ade.api.util.ImageUtil;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.constant.StretchType;
import net.sf.jasperreports.renderers.Renderable;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 *
 * @author dfuentes
 */
public class ImageBuilder extends ElementBuilder<Image, ComponentBuilder> {

  /**
   * Image builder
   * @param element image element
   * @param jasperReportBuilder  report builder
   * @return ComponentBuilder
   */
  @Override
  public ComponentBuilder build(@NotNull Image element, JasperReportBuilder jasperReportBuilder) {
    ComponentBuilder component = null;
    try {
      component = generateImage(element);
      if (element.getFooter() != null || element.getHeader() != null) {
        return generateVerticalList(element, component, jasperReportBuilder);
      }

    } catch (IOException e) {
      Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error parsing url to image");
    }

    return component;
  }

  @SuppressWarnings("unchecked")
  private VerticalListBuilder generateVerticalList(Image element, ComponentBuilder image, JasperReportBuilder reportBuilder) {
    VerticalListBuilder verticalList = cmp.verticalList();

    if (element.getHeader() != null) {
      verticalList.add((ComponentBuilder) getBuilderMapper()
              .getBuilder(element.getHeader().getClass())
              .build(element.getHeader(), reportBuilder));
    }

    if (image != null) {
      verticalList.add(image);
    }

    if (element.getFooter() != null) {
      verticalList.add(((ComponentBuilder) getBuilderMapper()
              .getBuilder(element.getFooter().getClass())
              .build(element.getFooter(), reportBuilder)));
    }

    return verticalList;
  }

  private ComponentBuilder generateImage(Image element) throws IOException {
    ComponentBuilder imageBuilder = null;

    // Image as input stream
    InputStream imageAsInputStream = ImageUtil.getImageAsInputStream(element);
    if (imageAsInputStream != null) {
      imageBuilder = cmp.image(imageAsInputStream)
              .setWidth(element.getSize())
              .setHeight(Math.round(element.getSize() * element.getScale()));
    } else if (element.getSVGImage() != null){
      // Image as renderable
      Renderable imageAsRenderable = ImageUtil.getImageAsRenderable(element);
      if (imageAsRenderable != null) {
        imageBuilder = cmp.image(imageAsRenderable)
                .setFixedDimension(element.getSize(), Math.round(element.getSize() * element.getScale()))
                .setImageScale(ImageScale.FILL_FRAME);
      }
    } else if (element.getExpression() != null) {
      imageBuilder = cmp.centerHorizontal(cmp.image(element.getExpression())
              .setFixedDimension(element.getSize(), element.getSize())
              .setStretchType(StretchType.NO_STRETCH))
              .setMinWidth(1).setStretchType(StretchType.CONTAINER_HEIGHT);
    }

    return imageBuilder;
  }
}
