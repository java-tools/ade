package com.almis.ade.api.fluid;

import com.almis.ade.api.fluid.engine.generic.JasperEngineBuilderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dfuentes
 */
public class PrintBeanBuilderService {

  // Autowired services
  private JasperEngineBuilderService jasperEngineBuilder;

  /**
   * Autowired constructor
   * @param jasperEngineBuilder jasper engine builder
   */
  @Autowired
  public PrintBeanBuilderService(JasperEngineBuilderService jasperEngineBuilder) {
    this.jasperEngineBuilder = jasperEngineBuilder;
  }

  /**
   * Print bean with Jasper Engine
   *
   * @return JasperEngineBuilderService
   */
  public JasperEngineBuilderService withJasper(){
    return jasperEngineBuilder;
  }
}
