package com.almis.ade.autoconfigure;

import com.almis.ade.api.ADE;
import com.almis.ade.api.engine.jasper.generation.builder.JasperDocumentBuilderService;
import com.almis.ade.api.fluid.PrintBeanBuilderService;
import com.almis.ade.api.fluid.ReportBeanBuilderService;
import com.almis.ade.api.fluid.engine.generic.JasperEngineBuilderService;
import com.almis.ade.api.fluid.engine.generic.TemplateExporterBuilderService;
import com.almis.ade.api.fluid.engine.specific.ReportExporterBuilderService;
import com.almis.ade.api.util.IconUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class AdeAutoConfiguration {

  // Autowired beans
  private Environment environment;

  /**
   * Autowired constructor
   * @param environment Environment
   */
  @Autowired
  public AdeAutoConfiguration(Environment environment) {
    this.environment = environment;
  }

  /**
   * ADE API
   * @param specificPrintBeanBuilder Specific print bean builder
   * @param printBeanBuilder Print bean builder
   * @return ADE API bean
   */
  @Bean
  @ConditionalOnMissingBean
  public ADE ade(ReportBeanBuilderService specificPrintBeanBuilder, PrintBeanBuilderService printBeanBuilder) {
    return new ADE(specificPrintBeanBuilder, printBeanBuilder);
  }

  /**
   * Initialize static utilities
   */
  @PostConstruct
  public void initializeStaticUtilities() {
    IconUtil.init(environment);
  }

  /////////////////////////////////////////////
  // SERVICES
  /////////////////////////////////////////////

  /**
   * Report bean exporter
   * @param reportExporterBuilderService Report exporter builder service
   * @return Report bean builder service
   */
  @Bean
  @ConditionalOnMissingBean
  public ReportBeanBuilderService reportBeanBuilderService(ReportExporterBuilderService reportExporterBuilderService) {
    return new ReportBeanBuilderService(reportExporterBuilderService);
  }

  /**
   * Report exporter builder service
   * @return Specific report Exporter Builder Service
   */
  @Bean
  @ConditionalOnMissingBean
  public ReportExporterBuilderService reportExporterBuilderService() {
    return new ReportExporterBuilderService();
  }

  /**
   * Print bean builder
   * @param jasperEngineBuilder engine builder
   * @return Print bean builder service
   */
  @Bean
  @ConditionalOnMissingBean
  public PrintBeanBuilderService printBeanBuilderService(JasperEngineBuilderService jasperEngineBuilder) {
    return new PrintBeanBuilderService(jasperEngineBuilder);
  }

  /**
   * Jasper Engine Builder Service
   * @param jasperDocumentBuilderService document builder
   * @param templateExporterBuilder template export builder
   * @return Jasper Engine Builder Service
   */
  @Bean
  @ConditionalOnMissingBean
  public JasperEngineBuilderService jasperEngineBuilderService(JasperDocumentBuilderService jasperDocumentBuilderService,
                                                               TemplateExporterBuilderService templateExporterBuilder) {
    return new JasperEngineBuilderService(jasperDocumentBuilderService, templateExporterBuilder);
  }

  /**
   * Jasper Engine Builder Service
   * @return Jasper Engine Builder Service
   */
  @Bean
  @ConditionalOnMissingBean
  public JasperDocumentBuilderService jasperDocumentBuilderService() {
    return new JasperDocumentBuilderService();
  }

  /**
   * Jasper Engine Builder Service
   * @return Jasper Engine Builder Service
   */
  @Bean
  @ConditionalOnMissingBean
  public TemplateExporterBuilderService templateExporterBuilderService() {
    return new TemplateExporterBuilderService();
  }
}
