package com.almis.ade.api;

import com.almis.ade.api.bean.input.SpecificPrintBean;
import com.almis.ade.api.fluid.PrintBeanBuilderService;
import com.almis.ade.api.fluid.SpecificPrintBeanBuilderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dfuentes
 */
public class ADE {

  // Autowired services
  private SpecificPrintBeanBuilderService specificPrintBeanBuilder;
  private PrintBeanBuilderService printBeanBuilder;

  /**
   * Autowired constructor
   * @param specificPrintBeanBuilder Specific print bean service
   * @param printBeanBuilder Print bean service
   */
  @Autowired
  public ADE(SpecificPrintBeanBuilderService specificPrintBeanBuilder, PrintBeanBuilderService printBeanBuilder) {
    this.specificPrintBeanBuilder = specificPrintBeanBuilder;
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
   * @param specificPrintBean specific print bean
   * @return SpecificPrintBeanBuilderService
   */
  public SpecificPrintBeanBuilderService specificPrintBean(SpecificPrintBean specificPrintBean) {
    return specificPrintBeanBuilder.initialize(specificPrintBean);
  }
}
