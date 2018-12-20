package com.almis.ade.api.fluid.engine.generic;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.bean.input.PrintBean;
import com.almis.ade.api.engine.jasper.generation.builder.JasperDocumentBuilderService;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import com.almis.ade.api.enumerate.Section;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 *
 * @author dfuentes
 */
public class JasperEngineBuilderService {

  // Autowired services
  private JasperDocumentBuilderService jasperDocumentBuilderService;
  private TemplateExporterBuilderService templateExporterBuilder;

  /**
   * Autowired constructor
   * @param jasperDocumentBuilderService
   * @param templateExporterBuilder
   */
  @Autowired
  public JasperEngineBuilderService(JasperDocumentBuilderService jasperDocumentBuilderService,
                                    TemplateExporterBuilderService templateExporterBuilder) {
    this.jasperDocumentBuilderService = jasperDocumentBuilderService;
    this.templateExporterBuilder = templateExporterBuilder;
  }

  /**
   * Set section builder mapping
   *
   * @param builderMapper
   * @return
   */
  public JasperEngineBuilderService setSectionBuilderMapping(Map<Section, ElementBuilder> builderMapper) {

    jasperDocumentBuilderService
            .getBuilderMapper()
            .setSectionBuilders(builderMapper);
    return this;
  }

  /**
   * Set builder mapping
   *
   * @param builderMapper
   * @return
   */
  public JasperEngineBuilderService setBuilderMapping(Map<Class<? extends Element>, ElementBuilder> builderMapper) {

    jasperDocumentBuilderService
            .getBuilderMapper()
            .setBuilders(builderMapper);
    return this;
  }

  /**
   * Set section builder mapping
   *
   * @param section
   * @param builder
   * @return
   */
  public JasperEngineBuilderService setSectionBuilderMapping(Section section, ElementBuilder builder) {
    jasperDocumentBuilderService
            .getBuilderMapper()
            .addSectionBuilder(section, builder);
    return this;
  }

  /**
   * Set builder mapping
   *
   * @param bean
   * @param builder
   * @return
   */
  public JasperEngineBuilderService setBuilderMapping(Class<? extends Element> bean, ElementBuilder builder) {
    jasperDocumentBuilderService
            .getBuilderMapper()
            .addBuilder(bean, builder);
    return this;
  }

  /**
   * Build current report and export to files
   *
   * @param printBean
   * @return
   */
  public TemplateExporterBuilderService buildAndExport(PrintBean printBean) {

    //Get current report object
    JasperReportBuilder reportBuilder = jasperDocumentBuilderService.build(printBean);

    return templateExporterBuilder.initialize(reportBuilder);
  }
}
