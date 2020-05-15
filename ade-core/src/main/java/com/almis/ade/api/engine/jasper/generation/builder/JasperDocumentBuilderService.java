package com.almis.ade.api.engine.jasper.generation.builder;

import com.almis.ade.api.bean.component.*;
import com.almis.ade.api.bean.component.grid.ReportColumn;
import com.almis.ade.api.bean.component.grid.ReportGrid;
import com.almis.ade.api.bean.component.grid.ReportHeader;
import com.almis.ade.api.bean.input.PrintBean;
import com.almis.ade.api.bean.section.PageFooter;
import com.almis.ade.api.bean.section.PageHeader;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.*;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.grid.ColumnBuilder;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.grid.GridBuilder;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.grid.HeaderBuilder;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.specific.PageFooterBuilder;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.specific.PageHeaderBuilder;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.template.GenericTemplateBuilder;
import com.almis.ade.api.engine.jasper.generation.builder.mapper.BuilderMapper;
import com.almis.ade.api.enumerate.Section;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.WhenNoDataType;

import java.util.ArrayList;
import java.util.Collection;

import static net.sf.dynamicreports.report.builder.DynamicReports.margin;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;

/**
 *
 * @author dfuentes
 */
public class JasperDocumentBuilderService {
  private BuilderMapper builderMapper;

  /**
   * Initialize variables
   *
   * @param printBean print bean
   * @return JasperDocumentBuilderService
   */
  public JasperDocumentBuilderService initialize(PrintBean printBean){
    // Initialize builder mapper
    builderMapper = BuilderMapper.getInstance();

    // Initialize element builders
    setDefaultElementBuilderMapping(printBean);

    // Initialize band builders
    setDefaultTemplateBuilderMapping();

    return this;
  }

  /**
   * Element builder mapper initializer
   */
  private void setDefaultElementBuilderMapping(PrintBean printBean) {

    // Default element builder mappings
    getBuilderMapper().addBuilder(Text.class, new TextBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(Image.class, new ImageBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(Icon.class, new IconBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(URL.class, new URLBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(Layout.class, new LayoutBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(PageBreak.class, new PageBreakBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(Paging.class, new PagingBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(Barcode.class, new BarcodeBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(QRCode.class, new QRCodeBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(CurrentDate.class, new CurrentDateBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(Line.class, new LineBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(Criterion.class, new CriterionBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(ReportGrid.class, new GridBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(ReportColumn.class, new ColumnBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(ReportHeader.class, new HeaderBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(PageHeader.class, new PageHeaderBuilder().setPrintBean(printBean));
    getBuilderMapper().addBuilder(PageFooter.class, new PageFooterBuilder().setPrintBean(printBean));
  }

  /**
   * Band builder mapper initializer
   */
  private void setDefaultTemplateBuilderMapping() {

    // Jasper specific sections, not applicable for other engines.
    getBuilderMapper().addSectionBuilder(Section.DEFAULT, new GenericTemplateBuilder());
  }

  /**
   * Returns the current builder mapper
   *
   * @return BuilderMapper
   */
  public BuilderMapper getBuilderMapper() {
    return builderMapper;
  }

  /**
   * Build current report
   *
   * @param printBean print bean
   * @return JasperReportBuilder
   */
  public JasperReportBuilder build(PrintBean printBean) {
    // Generate Jasper Report
    return exportFile(printBean);
  }

  /**
   * Exports current report to a file
   *
   * @param printBean print bean
   */
  @SuppressWarnings("unchecked")
  private JasperReportBuilder exportFile(PrintBean printBean) {
    JasperReportBuilder reportBuilder = report().setPageMargin(margin(printBean.getPageMargin()))
            .setPageFormat(printBean.getPageType(), printBean.getOrientation());

    // Initialize
    initialize(printBean);

    // Build each Band existing in the Builder mapper
    for (Section section : Section.values()) {
      if (section.getLayoutFromSection(printBean) != null) {
        section.addToSection(reportBuilder, (ComponentBuilder) getBuilderMapper()
                        .getSectionBuilder(section)
                        .build(section.getLayoutFromSection(printBean), reportBuilder)
        );
      }
    }

    Collection list = new ArrayList();
    list.add ("");
    reportBuilder.setDataSource(list);
    
    //Check if the NO_DATA bean exists, and set the pertinent behaviour
    if (printBean.getNoData() != null) {
      reportBuilder.setWhenNoDataType(WhenNoDataType.NO_DATA_SECTION);
    } else {
      reportBuilder.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL);
    }

    return reportBuilder;
  }
}
