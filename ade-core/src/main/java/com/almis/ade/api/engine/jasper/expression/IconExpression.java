package com.almis.ade.api.engine.jasper.expression;

import com.almis.ade.api.bean.input.DataBean;
import com.almis.ade.api.util.IconUtil;
import lombok.extern.log4j.Log4j2;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.renderers.Renderable;

import java.awt.*;

/**
 * Component expression to evaluate component values in columns
 */
@Log4j2
public class IconExpression extends AbstractSimpleExpression<Renderable> {
  private static final long serialVersionUID = 1L;
  private String field;

  /**
   * Expression constructor
   *
   * @param field Field to evaluate
   */
  public IconExpression(String field) {
    this.field = field;
  }

  /**
   * Evaluate the expression
   *
   * @param reportParameters Report parameters
   * @return Icon as renderable
   */
  @Override
  public Renderable evaluate(ReportParameters reportParameters) {
    Object icon;
    String iconName = null;
    String iconStyle = null;

    try {
      // Get icon name
      icon = reportParameters.getValue(field);
      if (icon instanceof DataBean) {
        DataBean iconData = (DataBean) icon;
        iconName = IconUtil.extractIcon(String.valueOf(iconData.getSingleValue("icon")));
        iconStyle = String.valueOf(iconData.getSingleValue("style"));
      } else {
        iconName = IconUtil.extractIcon((String) icon);
      }

      // Get icon path
      String iconSvg = IconUtil.getIconFromFile(iconName);
      iconSvg = iconSvg == null ? IconUtil.getIconFromFile("empty") : iconSvg;

      // Image as renderable
      Color iconColor = IconUtil.extractColor(iconStyle);
      return IconUtil.renderIcon(iconSvg, iconColor);
    } catch (Exception exc) {
      log.warn("Error retrieving icon file: {}", iconName, exc);
    }
    return null;
  }
}