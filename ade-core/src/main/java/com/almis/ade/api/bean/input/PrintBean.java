package com.almis.ade.api.bean.input;

import com.almis.ade.api.bean.component.Element;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;

/**
 * Print bean
 * Bean with all report elements
 */
public class PrintBean {

  private Element title;
  private Element pageHeader;
  private Element columnHeader;
  private Element detail;
  private Element columnFooter;
  private Element pageFooter;
  private Element summary;
  private Element lastPageFooter;
  private Element noData;
  private Element background;
  private String reportName;
  private String author;
  private PageOrientation orientation;
  private int pageMargin;
  private PageType pageType;

  public PrintBean() {
    this.setPageMargin(30);
    this.setPageType(PageType.A4);
  }

  /**
   * Get report name
   *
   * @return Report name
   */
  public String getReportName() {
    return reportName;
  }

  /**
   * Set report name
   *
   * @param reportName Report name
   *
   * @return this
   */
  public PrintBean setReportName(String reportName) {
    this.reportName = reportName;
    return this;
  }

  /**
   * Get author
   *
   * @return Author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Set author
   *
   * @param author Author
   *
   * @return this
   */
  public PrintBean setAuthor(String author) {
    this.author = author;
    return this;
  }

  /**
   * Get title
   *
   * @return Title
   */
  public Element getTitle() {
    return title;
  }

  /**
   * Set title
   *
   * @param title Title
   *
   * @return this
   */
  public PrintBean setTitle(Element title) {
    this.title = title;
    return this;
  }

  /**
   * Get page header
   *
   * @return Page header
   */
  public Element getPageHeader() {
    return pageHeader;
  }

  /**
   * Set page header
   *
   * @param pageHeader Page header
   *
   * @return this
   */
  public PrintBean setPageHeader(Element pageHeader) {
    this.pageHeader = pageHeader;
    return this;
  }

  /**
   * Get column header
   *
   * @return column header
   */
  public Element getColumnHeader() {
    return columnHeader;
  }

  /**
   * Set column header
   *
   * @param columnHeader Column header
   *
   * @return this
   */
  public PrintBean setColumnHeader(Element columnHeader) {
    this.columnHeader = columnHeader;
    return this;
  }

  /**
   * Get detail
   *
   * @return Detail
   */
  public Element getDetail() {
    return detail;
  }

  /**
   * Set detail
   *
   * @param detail Detail
   *
   * @return this
   */
  public PrintBean setDetail(Element detail) {
    this.detail = detail;
    return this;
  }

  /**
   * Get column footer
   *
   * @return column footer
   */
  public Element getColumnFooter() {
    return columnFooter;
  }

  /**
   * Set column footer
   *
   * @param columnFooter Column footer
   *
   * @return this
   */
  public PrintBean setColumnFooter(Element columnFooter) {
    this.columnFooter = columnFooter;
    return this;
  }

  /**
   * Get page footer
   *
   * @return Page footer
   */
  public Element getPageFooter() {
    return pageFooter;
  }

  /**
   * Set page footer
   *
   * @param pageFooter Page footer
   *
   * @return this
   */
  public PrintBean setPageFooter(Element pageFooter) {
    this.pageFooter = pageFooter;
    return this;
  }

  /**
   * Get summary
   *
   * @return Summary
   */
  public Element getSummary() {
    return summary;
  }

  /**
   * Set summary
   *
   * @param summary summary
   *
   * @return this
   */
  public PrintBean setSummary(Element summary) {
    this.summary = summary;
    return this;
  }

  /**
   * Get last page footer
   *
   * @return Page footer
   */
  public Element getLastPageFooter() {
    return lastPageFooter;
  }

  /**
   * Set last page footer
   *
   * @param lastPageFooter Page footer
   *
   * @return this
   */
  public PrintBean setLastPageFooter(Element lastPageFooter) {
    this.lastPageFooter = lastPageFooter;
    return this;
  }

  /**
   * Get no data
   *
   * @return no data
   */
  public Element getNoData() {
    return noData;
  }

  /**
   * Set no data
   *
   * @param noData No data
   *
   * @return this
   */
  public PrintBean setNoData(Element noData) {
    this.noData = noData;
    return this;
  }

  /**
   * Get background
   *
   * @return background
   */
  public Element getBackground() {
    return background;
  }

  /**
   * Set background
   *
   * @param background Background
   *
   * @return this
   */
  public PrintBean setBackground(Element background) {
    this.background = background;
    return this;
  }

  /**
   * Get page orientation
   *
   * @return Orientation
   */
  public PageOrientation getOrientation() {
    return orientation;
  }

  /**
   * Set page orientation
   *
   * @param orientation Orientation
   *
   * @return PrintBean
   */
  public PrintBean setOrientation(PageOrientation orientation) {
    this.orientation = orientation;
    return this;
  }

  /**
   * Get page margin
   *
   * @return margin of page
   */
  public int getPageMargin() {
    return pageMargin;
  }

  /**
   * Set page margin
   *
   * @param pageMargin page margin
   *
   * @return PrintBean
   */
  public PrintBean setPageMargin(int pageMargin) {
    this.pageMargin = pageMargin;
    return this;
  }

  /**
   * Get page type
   *
   * @return PageType
   */
  public PageType getPageType() {
    return pageType;
  }

  /**
   * Set page type
   *
   * @param pageType page type
   *
   * @return PrintBean
   */
  public PrintBean setPageType(PageType pageType) {
    this.pageType = pageType;
    return this;
  }
}