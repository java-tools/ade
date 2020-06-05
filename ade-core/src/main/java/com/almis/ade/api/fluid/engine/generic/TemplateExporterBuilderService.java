package com.almis.ade.api.fluid.engine.generic;

import com.almis.ade.api.util.JasperFileUtil;
import lombok.extern.log4j.Log4j2;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.*;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static net.sf.dynamicreports.report.builder.DynamicReports.exp;
import static net.sf.dynamicreports.report.builder.DynamicReports.export;

/**
 * Template exporter builder service
 */
@Log4j2
public class TemplateExporterBuilderService {
  @Value("${ade.document.default.path:documents}")
  private String defaultPath;
  @Value("${ade.document.default.name:document}")
  private String defaultName;
  @Value("${ade.document.extension.pattern:{extension}}")
  private String extensionPattern;

  private JasperReportBuilder reportBuilder;
  private Map<String, Object> data;
  private JRDataSource dataSource;

  /**
   * Initialize class
   *
   * @param reportBuilder report builder
   * @return TemplateExporterBuilderService
   */
  public TemplateExporterBuilderService initialize(JasperReportBuilder reportBuilder) {
    this.reportBuilder = reportBuilder;
    return this;
  }

  /**
   * set current document Name
   *
   * @param name document name
   * @return TemplateExporterBuilderService
   */
  public TemplateExporterBuilderService withName(String name) {
    this.defaultName = name;
    return this;
  }

  /**
   * Set current document save path
   *
   * @param path document path
   * @return TemplateExporterBuilderService
   */
  public TemplateExporterBuilderService withPath(String path) {
    this.defaultPath = path.endsWith(File.separator) ? path : path + File.separator;
    return this;
  }

  /**
   * Set data for report
   *
   * @param key   data key
   * @param value value data
   * @return TemplateExporterBuilderService
   */
  public TemplateExporterBuilderService withData(String key, Object value) {
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
   * @return TemplateExporterBuilderService
   */
  public TemplateExporterBuilderService withData(Map<String, Object> data) {
    this.data = data;
    return this;
  }

  /**
   * Set datasource for report
   *
   * @param dataSource report data source
   * @return TemplateExporterBuilderService
   */
  public TemplateExporterBuilderService withDataSource(JRDataSource dataSource) {
    this.dataSource = dataSource;
    return this;
  }

  /**
   * Get data for report
   *
   * @return data map
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
   * Retrieve template path
   *
   * @return Template path
   */
  public String getTemplatePath() {
    this.defaultPath = defaultPath.endsWith(File.separator) ? defaultPath : defaultPath + File.separator;

    if (Paths.get(defaultPath).toFile().mkdirs()) {
      //Throw exception, couldn't create directories
    }

    return defaultPath + defaultName + "." + extensionPattern;
  }

  /**
   * Export to JRXML
   *
   * @return TemplateExporterBuilderService
   */
  public TemplateExporterBuilderService toJRXML() {
    if (!Paths.get(getTemplatePath().replace(extensionPattern, "jrxml")).toFile().exists()) {
      // Save the template to a File
      File jrxmlFile = new File(getTemplatePath().replace(extensionPattern, "jrxml"));
      try (OutputStream jrxmlFileStream = new FileOutputStream(jrxmlFile)) {
        // Export to jasperPrint file
        JasperFileUtil.exportReportToJRXMLFile(reportBuilder, jrxmlFileStream);
        log.info("JRXML file generated");
      } catch (IOException | DRException exc) {
        log.error("Error exporting to JRXML - {}", getTemplatePath(), exc);
      }
    }
    return this;
  }

  /**
   * Export to JRPXML
   *
   * @return TemplateExporterBuilderService
   */
  public TemplateExporterBuilderService toJRPXML() {
    if (!Paths.get(getTemplatePath().replace(extensionPattern, "jrpxml")).toFile().exists()) {
      // Save the template to a File
      File jprxmlFile = new File(getTemplatePath().replace(extensionPattern, "jrpxml"));
      try (OutputStream jrpxmlFileStream = new FileOutputStream(jprxmlFile)) {
        // Export to jasperPrint file
        JasperFileUtil.exportReportToJRPXMLFile(reportBuilder, jrpxmlFileStream);
        log.info("JRPXML file generated");
      } catch (IOException | JRException | DRException exc) {
        log.error("Error exporting to JRPXML - {}", getTemplatePath(), exc);
      }
    }
    return this;
  }

  /**
   * Export to Jasper
   *
   * @return TemplateExporterBuilderService
   */
  public TemplateExporterBuilderService toJasper() {
    if (!Paths.get(getTemplatePath().replace(extensionPattern, "jasper")).toFile().exists()) {
      // Save the template to a File
      File jasperFile = new File(getTemplatePath().replace(extensionPattern, "jasper"));
      try (OutputStream jasperFileStream = new FileOutputStream(jasperFile)) {
        // Export to jasperPrint file
        JasperFileUtil.exportReportToJasperFile(reportBuilder, jasperFileStream);
        log.info("Jasper file generated");
      } catch (IOException | JRException | DRException exc) {
        log.error("Error exporting to Jasper - {}", getTemplatePath(), exc);
      }
    }
    return this;
  }

  /**
   * Export to PDF
   *
   * @return TemplateExporterBuilderService
   * @throws DRException DRException exception
   */
  public TemplateExporterBuilderService toPDF() throws DRException {
    JasperPdfExporterBuilder pdfExporterBuilder = export.pdfExporter(getTemplatePath().replace(extensionPattern, "pdf"));
    reportBuilder.toPdf(pdfExporterBuilder);
    return this;
  }

  /**
   * Export to XML
   *
   * @return TemplateExporterBuilderService
   * @throws DRException DRException exception
   */
  public TemplateExporterBuilderService toXML() throws DRException {
    JasperXmlExporterBuilder xmlExporterBuilder = export.xmlExporter(getTemplatePath().replace(extensionPattern, "xml"));
    reportBuilder.toXml(xmlExporterBuilder);
    return this;
  }

  /**
   * Export to HTML
   *
   * @return TemplateExporterBuilderService
   * @throws DRException DRException exception
   */
  public TemplateExporterBuilderService toHTML() throws DRException {
    JasperHtmlExporterBuilder htmlExporterBuilder = export.htmlExporter(getTemplatePath().replace(extensionPattern, "html"))
      .setAccessibleHtml(true);
    reportBuilder.toHtml(htmlExporterBuilder);
    return this;
  }

  /**
   * Export to CSV
   *
   * @return TemplateExporterBuilderService
   * @throws DRException DRException exception
   */
  public TemplateExporterBuilderService toCsv() throws DRException {
    JasperCsvExporterBuilder csvExporter = export.csvExporter(getTemplatePath().replace(extensionPattern, "csv"));
    reportBuilder.toCsv(csvExporter);
    return this;
  }

  /**
   * Export to DOCX
   *
   * @return TemplateExporterBuilderService
   * @throws DRException DRException exception
   */
  public TemplateExporterBuilderService toDocx() throws DRException {
    JasperDocxExporterBuilder docxExporterBuilder = export.docxExporter(getTemplatePath().replace(extensionPattern, "docx"))
      .setFramesAsNestedTables(true);
    reportBuilder.toDocx(docxExporterBuilder);
    return this;
  }

  /**
   * Export to API XLS
   *
   * @return TemplateExporterBuilderService
   * @throws DRException DRException exception
   */
  public TemplateExporterBuilderService toExcel() throws DRException {
    return toXlsx();
  }

  /**
   * Export to ODS
   *
   * @return TemplateExporterBuilderService
   * @throws DRException DRException exception
   */
  public TemplateExporterBuilderService toOds() throws DRException {
    JasperOdsExporterBuilder odsExporterBuilder = export.odsExporter(getTemplatePath().replace(extensionPattern, "ods"))
      .setFlexibleRowHeight(true);
    reportBuilder.toOds(odsExporterBuilder);
    return this;
  }

  /**
   * Export to PNG
   *
   * @return TemplateExporterBuilderService
   */
  public TemplateExporterBuilderService toPng() {
    return this;
  }

  /**
   * Export to RTF
   *
   * @return TemplateExporterBuilderService
   * @throws DRException DRException exception
   */
  public TemplateExporterBuilderService toRtf() throws DRException {
    JasperRtfExporterBuilder rtfExporterBuilder = export.rtfExporter(getTemplatePath().replace(extensionPattern, "rtf"));
    reportBuilder.toRtf(rtfExporterBuilder);
    return this;
  }

  /**
   * Export to TXT
   *
   * @return TemplateExporterBuilderService
   * @throws DRException DRException exception
   */
  public TemplateExporterBuilderService toText() throws DRException {
    JasperTextExporterBuilder textExporter = export.textExporter(getTemplatePath().replace(extensionPattern, "txt"));
    reportBuilder.toText(textExporter);
    return this;
  }

  /**
   * Export to XLS
   *
   * @return TemplateExporterBuilderService
   * @throws DRException DRException exception
   */
  public TemplateExporterBuilderService toXls() throws DRException {
    Map<String, String> patterns = new HashMap<>();
    patterns.put("java.util.date", "dd/MM/YYYY");
    JasperXlsExporterBuilder xlsExporter = export.xlsExporter(getTemplatePath().replace(extensionPattern, "xls"))
      .setIgnorePageMargins(true)
      .setWhitePageBackground(false)
      .setRemoveEmptySpaceBetweenRows(true)
      .setRemoveEmptySpaceBetweenColumns(true)
      .setColumnWidthRatio(4.0f)
      .setFontSizeFixEnabled(false)
      .setImageBorderFixEnabled(true)
      .setFormatPatternsMap(patterns)
      .setIgnoreGraphics(true)
      .setDetectCellType(true)
      .setWrapText(false);

    // Remove headers and footers
    reportBuilder
      .setPageHeaderPrintWhenExpression(exp.value(Boolean.FALSE))
      .setPageFooterPrintWhenExpression(exp.value(Boolean.FALSE))
      .ignorePageWidth()
      .ignorePagination()
      .rebuild()
      .toXls(xlsExporter);
    return this;
  }

  /**
   * Export to XLSX
   *
   * @return TemplateExporterBuilderService
   * @throws DRException DRException exception
   */
  public TemplateExporterBuilderService toXlsx() throws DRException {
    Map<String, String> patterns = new HashMap<>();
    patterns.put("java.util.date", "dd/MM/YYYY");
    JasperXlsxExporterBuilder xlsExporter = export.xlsxExporter(getTemplatePath().replace(extensionPattern, "xlsx"))
      .setIgnorePageMargins(true)
      .setWhitePageBackground(false)
      .setRemoveEmptySpaceBetweenRows(true)
      .setRemoveEmptySpaceBetweenColumns(true)
      .setColumnWidthRatio(4.0f)
      .setFontSizeFixEnabled(false)
      .setImageBorderFixEnabled(true)
      .setFormatPatternsMap(patterns)
      .setIgnoreGraphics(true)
      .setDetectCellType(true)
      .setWrapText(false);

    // Remove headers and footers
    reportBuilder
      .setPageHeaderPrintWhenExpression(exp.value(Boolean.FALSE))
      .setPageFooterPrintWhenExpression(exp.value(Boolean.FALSE))
      .ignorePageWidth()
      .ignorePagination()
      .rebuild()
      .toXlsx(xlsExporter);
    return this;
  }

  /**
   * Show generated report
   *
   * @return TemplateExporterBuilderService
   */
  public TemplateExporterBuilderService show() {
    try {
      this.reportBuilder
        .show();
    } catch (DRException exc) {
      log.error("Error showing output", exc);
    }
    return this;
  }

  /**
   * Show generated JRXML
   *
   * @return TemplateExporterBuilderService
   */
  public TemplateExporterBuilderService showJrxml() {
    try {
      this.reportBuilder
        .showJrXml();
    } catch (DRException exc) {
      log.error("Error showing JRXML", exc);
    }
    return this;
  }

  /**
   * Export to PDF Stream
   *
   * @param outputStream outputStream
   * @return TemplateExporterBuilderService
   * @throws DRException DRException exception
   */
  public TemplateExporterBuilderService toPDFStream(OutputStream outputStream) throws DRException {
    reportBuilder.setDataSource(getDataSource());
    reportBuilder.toPdf(outputStream);
    return this;
  }
}
