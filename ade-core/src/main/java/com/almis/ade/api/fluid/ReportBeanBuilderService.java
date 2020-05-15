package com.almis.ade.api.fluid;

import com.almis.ade.api.bean.input.ReportBean;
import com.almis.ade.api.bean.input.TemplateBean;
import com.almis.ade.api.fluid.engine.specific.ReportExporterBuilderService;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * SpecificPrintBeanBuilderService class
 *
 * @author dfuentes
 */
@Log4j2
@Data
@Accessors(chain = true)
@Service
public class ReportBeanBuilderService {

  // Autowired service
  private ReportExporterBuilderService reportExporterBuilderService;
  private ReportBean reportBean;

  /**
   * Autowired constructor
   *
   * @param reportExporterBuilderService Report exporter builder service
   */
  @Autowired
  public ReportBeanBuilderService(ReportExporterBuilderService reportExporterBuilderService) {
    this.reportExporterBuilderService = reportExporterBuilderService;
  }

  /**
   * Initialize class
   *
   * @param reportBean Specific report bean
   * @return SpecificPrintBeanBuilderService
   */
  public ReportBeanBuilderService initialize(ReportBean reportBean) {
    return setReportBean(reportBean);
  }

  /**
   * Build report and set data to export
   *
   * @return ReportExporterBuilderService
   */
  public ReportExporterBuilderService build() {
    List<JasperPrint> reportList = reportBean
      .getReportList()
      .stream()
      .map(this::compileTemplate)
      .filter(Objects::nonNull)
      .collect(Collectors.toList());
    return reportExporterBuilderService.initialize(reportList, reportBean.getReportPath(), reportBean.getReportName(), reportBean.getReportPassword());
  }

  /**
   * Compile template with data
   * @param templateBean Template bean
   * @return JasperPrint object
   */
  private JasperPrint compileTemplate(TemplateBean templateBean) {
    try {
      JasperReport report = JasperCompileManager.compileReport(templateBean.getTemplate().getInputStream());

      // Generate collection if defined
      JRDataSource data = new JREmptyDataSource();
      if (templateBean.getData() != null) {
        data = new JRBeanCollectionDataSource(templateBean.getData());
      }

      return JasperFillManager.fillReport(report, templateBean.getParameters(), data);
    } catch (Exception exc) {
      log.error("Error generating report from template: {}", templateBean.getTemplateName(), exc);
      return null;
    }
  }
}
