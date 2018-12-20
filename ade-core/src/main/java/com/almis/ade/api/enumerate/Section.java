package com.almis.ade.api.enumerate;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.bean.input.PrintBean;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.SplitType;

/**
 *
 * @author dfuentes
 */
public enum Section {
  TITLE, PAGE_HEADER, COLUMN_HEADER, DETAIL, COLUMN_FOOTER, PAGE_FOOTER, SUMMARY, LAST_PAGE_FOOTER, BACKGROUND, NO_DATA,
  //Used to set default builder for bands
  DEFAULT;

  /**
   * Add a component builder to a section
   * @param reportBuilder Report builder
   * @param componentBuilder Component builder
   * @return this
   */
  public JasperReportBuilder addToSection(JasperReportBuilder reportBuilder, ComponentBuilder componentBuilder) {
    switch (this) {
      case TITLE:
        reportBuilder.title(componentBuilder);
        break;
      case PAGE_HEADER:
        reportBuilder.pageHeader(componentBuilder);
        break;
      case COLUMN_HEADER:
        reportBuilder.columnHeader(componentBuilder);
        break;
      case DETAIL:
        reportBuilder.detail(componentBuilder);
        break;
      case COLUMN_FOOTER:
        reportBuilder.columnFooter(componentBuilder);
        break;
      case PAGE_FOOTER:
        reportBuilder.pageFooter(componentBuilder);
        break;
      case SUMMARY:
        reportBuilder.summary(componentBuilder);
        break;
      case LAST_PAGE_FOOTER:
        reportBuilder.lastPageFooter(componentBuilder);
        break;
      case BACKGROUND:
        reportBuilder.background(componentBuilder).setBackgroundSplitType(SplitType.STRETCH);
        break;
      case NO_DATA:
        reportBuilder.noData(componentBuilder);
        break;
      default:
        break;
    }
    return reportBuilder;
  }

  /**
   * Get a layout from a section
   * @param printBean Print bean
   * @return Section layout
   */
  public Element getLayoutFromSection(PrintBean printBean){
    switch (this){
      case TITLE:
        return printBean.getTitle();
      case PAGE_HEADER:
        return printBean.getPageHeader();
      case COLUMN_HEADER:
        return printBean.getColumnHeader();
      case DETAIL:
        return printBean.getDetail();
      case COLUMN_FOOTER:
        return printBean.getColumnFooter();
      case PAGE_FOOTER:
        return printBean.getPageFooter();
      case SUMMARY:
        return printBean.getSummary();
      case LAST_PAGE_FOOTER:
        return printBean.getLastPageFooter();
      case BACKGROUND:
        return printBean.getBackground();
      case NO_DATA:
        return printBean.getNoData();
      default:
        return null;
    }
  }
}
