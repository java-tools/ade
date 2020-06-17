package com.almis.ade.api.engine.jasper.generation.builder.component.element.grid;

import com.almis.ade.api.bean.component.Text;
import com.almis.ade.api.bean.component.grid.GridHeader;
import com.almis.ade.api.bean.component.grid.ReportColumn;
import com.almis.ade.api.bean.component.grid.ReportGrid;
import com.almis.ade.api.bean.component.grid.ReportHeader;
import com.almis.ade.api.bean.input.DataBean;
import com.almis.ade.api.bean.style.StyleTemplate;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.column.ColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnGridComponentBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import net.sf.dynamicreports.report.builder.style.ConditionalStyleBuilder;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.SplitType;

import javax.validation.constraints.NotNull;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * GridBuilder class
 *
 * @author dfuentes
 */
public class GridBuilder extends ElementBuilder<ReportGrid, ComponentBuilder> {

  /**
   * Get grid builder
   *
   * @param element             ReportGrid element
   * @param jasperReportBuilder report builder
   * @return ComponentBuilder
   */
  @Override
  @SuppressWarnings("unchecked")
  public ComponentBuilder build(@NotNull ReportGrid element, JasperReportBuilder jasperReportBuilder) {

    List<ColumnGridComponentBuilder> superHeaders = new ArrayList<>();
    List<ConditionalStyleBuilder> conditionalStyleBuilderList = new ArrayList<>();
    boolean thereAreHeaders = false;
    JasperReportBuilder gridReport = report()
      .setTemplate(StyleTemplate.REPORT_TEMPLATE);
    SubreportBuilder gridSubReport = cmp.subreport(gridReport);

    // Set subreport orientation if defined
    if (element.getOrientation() != null) {
      gridReport.setPageFormat(PageType.A4, element.getOrientation());
    }

    // Set report text style
    if (element.getFontSize() != null) {
      gridReport.setColumnTitleStyle(stl.style(StyleTemplate.COLUMN_TITLE_STYLE)
        .setFontSize(element.getFontSize()));
    }

    // Set stretch type
    gridReport.setTitleSplitType(SplitType.PREVENT);
    gridReport.setColumnHeaderSplitType(SplitType.PREVENT);
    gridReport.setDetailSplitType(SplitType.PREVENT);

    // Generate conditional styles
    if (element.getStyleColumn() != null) {
      FieldBuilder<DataBean> styleColumn = DynamicReports.field(element.getStyleColumn().getField(), String.class);
      gridReport.fields(styleColumn);
      conditionalStyleBuilderList.add(stl.conditionalStyle(cnd.equal(styleColumn, new DataBean(JsonNodeFactory.instance.objectNode().put("value", "SUBTOTAL").put("label", "SUBTOTAL"))))
        .setBackgroundColor(new Color(238, 238, 238)));
      conditionalStyleBuilderList.add(stl.conditionalStyle(cnd.equal(styleColumn, new DataBean(JsonNodeFactory.instance.objectNode().put("value", "TOTAL").put("label", "TOTAL"))))
        .bold()
        .setTopBorder(stl.pen1Point()
          .setLineWidth(2f)
          .setLineColor(new Color(204, 204, 204)))
        .setBackgroundColor(new Color(221, 221, 221)));
    }

    // Set grid title
    if (element.getTitle() != null) {
      gridReport.addTitle((ComponentBuilder) getBuilderMapper()
        .getBuilder(Text.class)
        .build(element.getTitle(), jasperReportBuilder));
    }

    for (GridHeader gridHeader : element.getGridHeaders()) {
      if (gridHeader instanceof ReportHeader) {
        thereAreHeaders = true;
        ReportHeader header = (ReportHeader) gridHeader;
        // Add header to list
        ColumnTitleGroupBuilder groupBuilder = (ColumnTitleGroupBuilder) getBuilderMapper()
          .getBuilder(header.getClass())
          .build(header, gridReport);
        superHeaders.add(groupBuilder);

        // For each column in the header, add it to columns
        for (ReportColumn column : header.getColumns()) {
          groupBuilder.add(getColumnBuilder(element, column, gridReport, conditionalStyleBuilderList));
        }
      } else if (gridHeader instanceof ReportColumn) {
        superHeaders.add(getColumnBuilder(element, (ReportColumn) gridHeader, gridReport, conditionalStyleBuilderList));
      }
    }

    // Add column grid if there are headers
    if (thereAreHeaders) {
      gridReport.columnGrid(superHeaders.toArray(new ColumnGridComponentBuilder[0]));
    }

    // Add grid data
    gridSubReport.setDataSource(element.getDataSource());
    return gridSubReport;
  }

  /**
   * Retrieve column builder
   *
   * @param grid       Grid
   * @param column     Column
   * @param gridReport Grid report
   * @return Column builder
   */
  @SuppressWarnings("unchecked")
  private ColumnBuilder getColumnBuilder(ReportGrid grid, ReportColumn column, JasperReportBuilder gridReport, List<ConditionalStyleBuilder> conditionalStyleBuilders) {
    // Set conditional styles
    conditionalStyleBuilders.forEach(conditionalStyleBuilder -> column.getStyle().addConditionalStyle(conditionalStyleBuilder));

    // Set report text style
    column.setFontSize(grid.getFontSize());

    // Add column
    ColumnBuilder columnBuilder = (ColumnBuilder) getBuilderMapper()
      .getBuilder(column.getClass())
      .build(column, gridReport);

    gridReport.addColumn(columnBuilder);
    return columnBuilder;
  }
}
