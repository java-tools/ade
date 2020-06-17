package com.almis.ade.api.engine.jasper.expression;

import com.almis.ade.api.bean.input.DataBean;
import com.almis.ade.api.util.FileUtil;
import com.almis.ade.api.util.ImageUtil;
import lombok.extern.log4j.Log4j2;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.renderers.Renderable;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Component expression to evaluate component values in columns
 */
@Log4j2
public class ImageExpression extends AbstractSimpleExpression<Renderable> {
  private static final long serialVersionUID = 1L;
  private String field;

  /**
   * ImageExpression constructor
   *
   * @param field field
   */
  public ImageExpression(String field) {
    this.field = field;
  }

  /**
   * Evaluate component
   *
   * @param reportParameters report parameters
   * @return Renderable
   */
  @Override
  public Renderable evaluate(ReportParameters reportParameters) {
    // Get image path
    Object image = reportParameters.getValue(field);
    String imagePath;
    if (image instanceof DataBean) {
      imagePath = String.valueOf(((DataBean) image).getSingleValue("image"));
    } else {
      imagePath = String.valueOf(image);
    }
    try (InputStream inputStream = FileUtil.getResourceAsStream(imagePath)) {
      // Get image as svg
      if (inputStream != null) {
        String imageSvg = new String(IOUtils.toByteArray(inputStream), StandardCharsets.UTF_8);

        // Image as renderable
        return ImageUtil.getSvgImageAsRenderable(imageSvg);
      }
    } catch (IOException exc) {
      log.warn("Error retrieving image file: {}", imagePath, exc);
    }
    return null;
  }
}