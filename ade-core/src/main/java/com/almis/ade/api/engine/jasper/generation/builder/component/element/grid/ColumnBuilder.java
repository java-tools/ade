package com.almis.ade.api.engine.jasper.generation.builder.component.element.grid;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.bean.component.Icon;
import com.almis.ade.api.bean.component.Image;
import com.almis.ade.api.bean.component.grid.ReportColumn;
import com.almis.ade.api.bean.input.DataBean;
import com.almis.ade.api.bean.style.StyleTemplate;
import com.almis.ade.api.engine.jasper.expression.ComplexExpression;
import com.almis.ade.api.engine.jasper.expression.IconExpression;
import com.almis.ade.api.engine.jasper.expression.ImageExpression;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.BooleanComponentType;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 *
 * @author dfuentes
 */
public class ColumnBuilder extends ElementBuilder<ReportColumn, net.sf.dynamicreports.report.builder.column.ColumnBuilder> {

  /**
   *
   * @param element
   * @param reportBuilder
   * @return
   */
  @Override
  public net.sf.dynamicreports.report.builder.column.ColumnBuilder build(@NotNull ReportColumn element, JasperReportBuilder reportBuilder) {
    net.sf.dynamicreports.report.builder.column.ColumnBuilder columnBuilder;
    String label = element.getLabel() == null ? "" : element.getLabel();
    if (element.getType() != null) {
      switch (element.getType()) {
        case INTEGER:
          columnBuilder = col.column(label, element.getField(), type.integerType())
                  .setWidth(element.getWidth());
          break;
        case LONG:
          columnBuilder = col.column(label, element.getField(), type.longType())
                  .setWidth(element.getWidth());
          break;
        case FLOAT:
          columnBuilder = col.column(label, element.getField(), type.floatType())
                  .setWidth(element.getWidth());
          break;
        case DOUBLE:
          columnBuilder = col.column(label, element.getField(), type.doubleType())
                  .setWidth(element.getWidth());
          break;
        case BIGINTEGER:
          columnBuilder = col.column(label, element.getField(), type.bigIntegerType())
                  .setWidth(element.getWidth());
          break;
        case BIGDECIMAL:
          columnBuilder = col.column(label, element.getField(), type.bigDecimalType())
                  .setWidth(element.getWidth());
          break;
        case DATE:
          columnBuilder = col.column(label, element.getField(), type.dateType())
                  .setWidth(element.getWidth());
          break;
        case ICON:
          columnBuilder = getIconColumn(label, element, reportBuilder);
          break;
        case IMAGE:
          columnBuilder = getImageColumn(label, element, reportBuilder);
          break;
        case BOOLEAN:
          Integer checkboxSize = element.getFontSize()  != null ? element.getFontSize() : Icon.Size.VERY_SMALL.getSize();
          columnBuilder = col.booleanColumn(label, element.getField())
                  .setMinWidth(checkboxSize + 4)
                  .setWidth(element.getWidth() / 2)
                  .setComponentType(BooleanComponentType.IMAGE_CHECKBOX_1)
                  .setImageDimension(checkboxSize, checkboxSize);
          break;
        case PERCENTAGE:
          columnBuilder = col.column(label, element.getField(), type.percentageType())
                  .setWidth(element.getWidth());
          break;
        case OBJECT:
          columnBuilder = col.column(label, new ComplexExpression(element.getField()))
                  .setWidth(element.getWidth());
          reportBuilder.addField(element.getField(), DataBean.class);
          break;
        case STRING:
        default:
          columnBuilder = col.column(label, element.getField(), type.stringType())
                  .setWidth(element.getWidth())
                  .setStretchWithOverflow(true);
          break;
      }
    } else {
      columnBuilder = col.column(label, element.getField(), type.stringType())
              .setWidth(element.getWidth());
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
    }

    // Set column style
    columnBuilder.setStyle(element.getStyle());

    // Retrieve column builder
    return columnBuilder;
  }

  /**
   * Get icon column
   * @param label
   * @param element
   * @param reportBuilder
   * @return
   */
  private net.sf.dynamicreports.report.builder.column.ColumnBuilder getIconColumn(String label, ReportColumn element, JasperReportBuilder reportBuilder) {
    Integer iconSize = element.getFontSize()  != null ? element.getFontSize() + 2 : Icon.Size.SMALL.getSize();
    Icon icon = new Icon(element.getField())
      .setSize(iconSize)
      .setExpression(new IconExpression(element.getField()));
    net.sf.dynamicreports.report.builder.column.ColumnBuilder columnBuilder = getDynamicColumn(Icon.class, icon, iconSize, label, element, reportBuilder);
    reportBuilder.addField(element.getField(), DataBean.class);
    return columnBuilder;
  }

  /**
   * Get image column
   * @param label
   * @param element
   * @param reportBuilder
   * @return
   */
  private net.sf.dynamicreports.report.builder.column.ColumnBuilder getImageColumn(String label, ReportColumn element, JasperReportBuilder reportBuilder) {
    Integer imageSize = element.getFontSize()  != null ? element.getFontSize() + 4 : Image.Size.VERY_SMALL.getSize();
    Image image = new Image(element.getField())
      .setSize(imageSize)
      .setExpression(new ImageExpression(element.getField()));
    net.sf.dynamicreports.report.builder.column.ColumnBuilder columnBuilder = getDynamicColumn(Image.class, image, imageSize, label, element, reportBuilder);
    reportBuilder.addField(element.getField(), DataBean.class);
    return columnBuilder;
  }

  /**
   * Get dynamic column
   * @param builderClass Builder class
   * @param columnElement Column element
   * @param size Size
   * @param label Label
   * @param element Element
   * @param reportBuilder Report builder
   * @return
   */
  @SuppressWarnings("unchecked")
  private net.sf.dynamicreports.report.builder.column.ColumnBuilder getDynamicColumn(Class builderClass, Element columnElement, Integer size, String label, ReportColumn element, JasperReportBuilder reportBuilder) {
    return col.componentColumn(label, (ComponentBuilder) getBuilderMapper()
      .getBuilder(builderClass)
      .build(columnElement, reportBuilder))
      .setMinWidth(size + 4)
      .setWidth(element.getWidth() / 2);
  }
}
