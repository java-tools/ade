package com.almis.ade.autoconfigure;

import com.almis.ade.api.ADE;
import com.almis.ade.api.engine.jasper.generation.builder.JasperDocumentBuilderService;
import com.almis.ade.api.fluid.PrintBeanBuilderService;
import com.almis.ade.api.fluid.SpecificPrintBeanBuilderService;
import com.almis.ade.api.fluid.engine.generic.JasperEngineBuilderService;
import com.almis.ade.api.fluid.engine.generic.TemplateExporterBuilderService;
import com.almis.ade.api.fluid.engine.specific.SpecificTemplateDataBuilderService;
import com.almis.ade.api.fluid.engine.specific.SpecificTemplateExporterBuilderService;
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
  public ADE ade(SpecificPrintBeanBuilderService specificPrintBeanBuilder, PrintBeanBuilderService printBeanBuilder) {
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
   * Specific print bean builder
   * @return Specific print bean builder service
   */
  @Bean
  @ConditionalOnMissingBean
  public SpecificPrintBeanBuilderService specificPrintBeanBuilderService(SpecificTemplateDataBuilderService specificTemplateDataBuilderService) {
    return new SpecificPrintBeanBuilderService(specificTemplateDataBuilderService);
  }

  /**
   * Specific Template Data Builder Service
   * @return Specific Template Data Builder Service
   */
  @Bean
  @ConditionalOnMissingBean
  public SpecificTemplateDataBuilderService specificTemplateDataBuilderService(SpecificTemplateExporterBuilderService specificTemplateExporterBuilderService) {
    return new SpecificTemplateDataBuilderService(specificTemplateExporterBuilderService);
  }

  /**
   * Specific Template Exporter Builder Service
   * @return Specific Template Exporter Builder Service
   */
  @Bean
  @ConditionalOnMissingBean
  public SpecificTemplateExporterBuilderService specificTemplateExporterBuilderService() {
    return new SpecificTemplateExporterBuilderService();
  }

  /**
   * Print bean builder
   * @return Print bean builder service
   */
  @Bean
  @ConditionalOnMissingBean
  public PrintBeanBuilderService printBeanBuilderService(JasperEngineBuilderService jasperEngineBuilder) {
    return new PrintBeanBuilderService(jasperEngineBuilder);
  }

  /**
   * Jasper Engine Builder Service
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
