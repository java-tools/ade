package com.almis.ade.api.fluid.engine.specific;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * SpecificTemplateDataBuilderService class
 * @author dfuentes
 */
public class SpecificTemplateDataBuilderService {
  @Value ("${ade.document.default.path:documents}")
  private String defaultPath;
  @Value ("${ade.document.default.name:document}")
  private String defaultName;

  // Autowired services
  private SpecificTemplateExporterBuilderService specificTemplateExporterBuilderService;

  private JasperReport reportBuilder;
  private String token;
  private Map<String, Object> data;
  private JRDataSource dataSource;

  /**
   * Autowired constructor
   * @param specificTemplateExporterBuilderService specificTemplateExporterBuilderService
   */
  @Autowired
  public SpecificTemplateDataBuilderService(SpecificTemplateExporterBuilderService specificTemplateExporterBuilderService) {
    this.specificTemplateExporterBuilderService = specificTemplateExporterBuilderService;
  }

  /**
   * initialize class
   *
   * @param reportBuilder report builder
   * @param token token
   * @return SpecificTemplateDataBuilderService
   */
  public SpecificTemplateDataBuilderService initialize(JasperReport reportBuilder, String token) {
    this.token = token;
    this.reportBuilder = reportBuilder;
    return this;
  }

  /**
   * set current document Name
   *
   * @param name name
   *
   * @return SpecificTemplateDataBuilderService
   */
  public SpecificTemplateDataBuilderService withName(String name) {
    this.defaultName = name;
    return this;
  }

  /**
   * Set current document save path
   *
   * @param path path
   *
   * @return SpecificTemplateDataBuilderService
   */
  public SpecificTemplateDataBuilderService withPath(String path) {
    this.defaultPath = path.endsWith(File.separator) ? path : path + File.separator;
    return this;
  }

  /**
   * Set data for report
   *
   * @param key data key
   * @param value data value
   *
   * @return SpecificTemplateDataBuilderService
   */
  public SpecificTemplateDataBuilderService withData(String key, Object value) {
    if (data == null) {
      data = new HashMap<>();
    }
    data.put(key, value);
    return this;
  }

  /**
   * Set data for report
   *
   * @param data report data
   *
   * @return SpecificTemplateDataBuilderService
   */
  public SpecificTemplateDataBuilderService withData(Map<String, Object> data) {
    if (data == null) {
      this.data = data;
    }
    return this;
  }

  /**
   * Set datasource for report
   *
   * @param dataSource report data source
   *
   * @return SpecificTemplateDataBuilderService
   */
  public SpecificTemplateDataBuilderService withDataSource(JRDataSource dataSource) {
    this.dataSource = dataSource;
    return this;
  }

  /**
   * Get data for report
   *
   * @return Data map
   */
  public Map<String, Object> getData() {
    return data;
  }

  /**
   * Get datasource for report
   *
   * @return JRDataSource
   */
  public JRDataSource getDataSource() {
    return dataSource;
  }

  /**
   * Returns exporter builder instance
   *
   * @return SpecificTemplateExporterBuilderService
   * @throws JRException JRException exception
   */
  public SpecificTemplateExporterBuilderService export() throws JRException {
    JasperPrint report = JasperFillManager.fillReport(this.reportBuilder, getData(), getDataSource());
    return specificTemplateExporterBuilderService.initialize(report, this.defaultPath, this.defaultName, this.token);
  }
}
