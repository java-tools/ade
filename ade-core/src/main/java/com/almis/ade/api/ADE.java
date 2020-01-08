package com.almis.ade.api;

import com.almis.ade.api.bean.input.ReportBean;
import com.almis.ade.api.fluid.PrintBeanBuilderService;
import com.almis.ade.api.fluid.ReportBeanBuilderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ADE API Base File
 * @author dfuentes
 */
public class ADE {

  // Autowired services
  private ReportBeanBuilderService reportBeanBuilderService;
  private PrintBeanBuilderService printBeanBuilder;

  /**
   * Autowired constructor
   * @param reportBeanBuilderService Specific print bean service
   * @param printBeanBuilder Print bean service
   */
  @Autowired
  public ADE(ReportBeanBuilderService reportBeanBuilderService, PrintBeanBuilderService printBeanBuilder) {
    this.reportBeanBuilderService = reportBeanBuilderService;
    this.printBeanBuilder = printBeanBuilder;
  }

  /**
   * Print dynamically generated report
   *
   * @return PrintBeanBuilderService
   */
  public PrintBeanBuilderService printBean() {
    return printBeanBuilder;
  }

  /**
   * Print report from given JRXML file
   *
   * @param reportBean specific report bean
   * @return ReportBeanBuilderService
   */
  public ReportBeanBuilderService reportBean(ReportBean reportBean) {
    return reportBeanBuilderService.initialize(reportBean);
  }
}
