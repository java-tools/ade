package com.almis.ade.api.engine.jasper.generation.builder.component.element.grid;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.bean.component.Icon;
import com.almis.ade.api.bean.component.Image;
import com.almis.ade.api.bean.component.grid.ReportColumn;
import com.almis.ade.api.bean.input.DataBean;
import com.almis.ade.api.bean.style.StyleTemplate;
import com.almis.ade.api.engine.jasper.expression.*;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import com.almis.ade.api.enumerate.ColumnType;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.BooleanComponentType;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

/**
 * ColumnBuilder class
 *
 * @author dfuentes
 */
public class ColumnBuilder extends ElementBuilder<ReportColumn, net.sf.dynamicreports.report.builder.column.ColumnBuilder> {

  private static final Integer ROW_HEIGHT = 10;

  /**
   * Build column
   *
   * @param element       column element
   * @param reportBuilder report builder
   * @return ColumnBuilder
   */
  @Override
  public net.sf.dynamicreports.report.builder.column.ColumnBuilder build(@NotNull ReportColumn element, JasperReportBuilder reportBuilder) {
    net.sf.dynamicreports.report.builder.column.ColumnBuilder columnBuilder;
    String label = element.getLabel() == null ? "" : element.getLabel();
    switch (Optional.ofNullable(element.getType()).orElse(ColumnType.STRING)) {
      case INTEGER:
      case LONG:
      case BIGINTEGER:
      case FLOAT:
      case DOUBLE:
      case BIGDECIMAL:
        columnBuilder = col.column(label, new ComplexNumberExpression(element.getField(), element.getType()))
          .setWidth(element.getWidth())
          .setFixedHeight(ROW_HEIGHT)
          .setTitleMinHeight(ROW_HEIGHT)
          .setStretchWithOverflow(true);
        break;
      case DATE:
        columnBuilder = col.column(label, new ComplexDateExpression(element.getField()))
          .setWidth(element.getWidth())
          .setFixedHeight(ROW_HEIGHT)
          .setTitleMinHeight(ROW_HEIGHT)
          .setStretchWithOverflow(true);
        break;
      case ICON:
        columnBuilder = getIconColumn(label, element, reportBuilder);
        break;
      case IMAGE:
        columnBuilder = getImageColumn(label, element, reportBuilder);
        break;
      case BOOLEAN:
        Integer checkboxSize = element.getFontSize() != null ? element.getFontSize() : Icon.Size.VERY_SMALL.getSize();
        columnBuilder = col.booleanColumn(label, new ComplexBooleanExpression(element.getField()))
          .setMinWidth(checkboxSize + 4)
          .setWidth(element.getWidth() / 2)
          .setFixedHeight(ROW_HEIGHT)
          .setTitleMinHeight(ROW_HEIGHT)
          .setComponentType(BooleanComponentType.IMAGE_CHECKBOX_1)
          .setImageDimension(checkboxSize, checkboxSize);
        break;
      case PERCENTAGE:
        reportBuilder.addField(element.getField(), DataBean.class);
        columnBuilder = col.column(label, new ComplexDoubleExpression(element.getField()))
          .setFixedHeight(ROW_HEIGHT)
          .setTitleMinHeight(ROW_HEIGHT)
          .setWidth(element.getWidth())
          .setStretchWithOverflow(true);
        break;
      case OBJECT:
      case STRING:
      default:
        columnBuilder = col.column(label, new ComplexExpression(element.getField()))
          .setWidth(element.getWidth())
          .setFixedHeight(ROW_HEIGHT)
          .setTitleMinHeight(ROW_HEIGHT)
          .setStretchWithOverflow(true);
    }

    // Add title style
    if (element.getFontSize() != null) {
      element.getStyle()
        .setFontSize(element.getFontSize());
      columnBuilder.setTitleStyle(stl.style(StyleTemplate.COLUMN_TITLE_STYLE)
        .setFontSize(element.getFontSize()));
    }

    // Horizontal text alignment
    if (element.getAlign() != null) {
      element.getStyle().setHorizontalTextAlignment(element.getAlign());
      element.getStyle().setVerticalTextAlignment(VerticalTextAlignment.MIDDLE);
    }

    // Set column style
    columnBuilder.setStyle(element.getStyle())
      .setTitleStretchWithOverflow(true);

    // Add field to report
    reportBuilder.addField(element.getField(), DataBean.class);

    // Retrieve column builder
    return columnBuilder;
  }

  /**
   * Get icon column
   *
   * @param label         icon label
   * @param element       icon element
   * @param reportBuilder report builder
   * @return ColumnBuilder
   */
  private net.sf.dynamicreports.report.builder.column.ColumnBuilder getIconColumn(String label, ReportColumn element, JasperReportBuilder reportBuilder) {
    Integer iconSize = element.getFontSize() != null ? element.getFontSize() + 2 : Icon.Size.SMALL.getSize();
    Icon icon = new Icon(element.getField())
      .setSize(iconSize)
      .setExpression(new IconExpression(element.getField()));
    return getDynamicColumn(Icon.class, icon, iconSize, label, element, reportBuilder);
  }

  /**
   * Get image column
   *
   * @param label         column image label
   * @param element       column image element
   * @param reportBuilder report builder
   * @return ColumnBuilder
   */
  private net.sf.dynamicreports.report.builder.column.ColumnBuilder getImageColumn(String label, ReportColumn element, JasperReportBuilder reportBuilder) {
    Integer imageSize = element.getFontSize() != null ? element.getFontSize() + 4 : Image.Size.VERY_SMALL.getSize();
    Image image = new Image(element.getField())
      .setSize(imageSize)
      .setExpression(new ImageExpression(element.getField()));
    return getDynamicColumn(Image.class, image, imageSize, label, element, reportBuilder);
  }

  /**
   * Get dynamic column
   *
   * @param builderClass  Builder class
   * @param columnElement Column element
   * @param size          Size
   * @param label         Label
   * @param element       Element
   * @param reportBuilder Report builder
   * @return ColumnBuilder
   */
  @SuppressWarnings("unchecked")
  private net.sf.dynamicreports.report.builder.column.ColumnBuilder getDynamicColumn(Class builderClass, Element columnElement, Integer size, String label, ReportColumn element, JasperReportBuilder reportBuilder) {
    return col.componentColumn(label, (ComponentBuilder) getBuilderMapper()
      .getBuilder(builderClass)
      .build(columnElement, reportBuilder))
      .setMinWidth(size + 4)
      .setWidth(element.getWidth() / 2)
      .setFixedHeight(ROW_HEIGHT)
      .setTitleMinHeight(ROW_HEIGHT)
      .setTitleStretchWithOverflow(false);
  }
}
