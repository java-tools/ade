package com.almis.ade.api.fluid.engine.generic;

import com.almis.ade.api.util.JasperFileUtil;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.*;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class TemplateExporterBuilderService {
  @Value ("${ade.document.default.path:documents}")
  private String defaultPath;
  @Value ("${ade.document.default.name:document}")
  private String defaultName;
  @Value ("${ade.document.extension.pattern:{extension}}")
  private String extensionPattern;

  private final Logger logger = LogManager.getLogger(this.getClass());

  private JasperReportBuilder reportBuilder;
  private Map<String, Object> data;
  private JRDataSource dataSource;

  /**
   * Initialize class
   *
   * @param reportBuilder
   * @return
   */
  public TemplateExporterBuilderService initialize(JasperReportBuilder reportBuilder) {
    this.reportBuilder = reportBuilder;
    return this;
  }

  /**
   * set current document Name
   *
   * @param name
   *
   * @return
   */
  public TemplateExporterBuilderService withName(String name) {
    this.defaultName = name;
    return this;
  }

  /**
   * Set current document save path
   *
   * @param path
   *
   * @return
   */
  public TemplateExporterBuilderService withPath(String path) {
    this.defaultPath = path.endsWith(File.separator) ? path : path + File.separator;
    return this;
  }

  /**
   * Set data for report
   *
   * @param key
   * @param value
   *
   * @return
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
   * @param data
   *
   * @return
   */
  public TemplateExporterBuilderService withData(Map<String, Object> data) {
    this.data = data;
    return this;
  }

  /**
   * Set datasource for report
   *
   * @param dataSource
   *
   * @return
   */
  public TemplateExporterBuilderService withDataSource(JRDataSource dataSource) {
    this.dataSource = dataSource;
    return this;
  }

  /**
   * Get data for report
   *
   * @return
   */
  public Map<String, Object> getData() {
    return data;
  }

  /**
   * Get datasource for report
   *
   * @return
   */
  public JRDataSource getDataSource() {
    return dataSource;
  }

  /**
   * Retrieve template path
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
   * @return
   */
  public TemplateExporterBuilderService toJRXML() {
    if (!Paths.get(getTemplatePath().replace(extensionPattern, "jrxml")).toFile().exists()) {
      // Save the template to a File
      File jrxmlFile =  new File(getTemplatePath().replace(extensionPattern, "jrxml"));
      try (OutputStream jrxmlFileStream = new FileOutputStream(jrxmlFile)) {
        // Export to jasperPrint file
        JasperFileUtil.exportReportToJRXMLFile(reportBuilder, jrxmlFileStream);
        logger.info("JRXML file generated");
      } catch (IOException | DRException exc) {
        logger.error("Error exporting to JRXML - {0}", getTemplatePath(), exc);
      }
    }
    return this;
  }

  /**
   * Export to JRPXML
   *
   * @return
   */
  public TemplateExporterBuilderService toJRPXML() {
    if (!Paths.get(getTemplatePath().replace(extensionPattern, "jrpxml")).toFile().exists()) {
      // Save the template to a File
      File jprxmlFile =  new File(getTemplatePath().replace(extensionPattern, "jrpxml"));
      try (OutputStream jrpxmlFileStream = new FileOutputStream(jprxmlFile)) {
        // Export to jasperPrint file
        JasperFileUtil.exportReportToJRPXMLFile(reportBuilder, jrpxmlFileStream);
        logger.info( "JRPXML file generated");
      } catch (IOException | JRException | DRException exc) {
        logger.error("Error exporting to JRPXML - {0}", getTemplatePath(), exc);
      }
    }
    return this;
  }

  /**
   * Export to Jasper
   *
   * @return
   */
  public TemplateExporterBuilderService toJasper() {
    if (!Paths.get(getTemplatePath().replace(extensionPattern, "jasper")).toFile().exists()) {
      // Save the template to a File
      File jasperFile =  new File(getTemplatePath().replace(extensionPattern, "jasper"));
      try (OutputStream jasperFileStream = new FileOutputStream(jasperFile)) {
        // Export to jasperPrint file
        JasperFileUtil.exportReportToJasperFile(reportBuilder, jasperFileStream);
        logger.info("Jasper file generated");
      } catch (IOException | JRException | DRException exc) {
        logger.error("Error exporting to Jasper - {0}", getTemplatePath(), exc);
      }
    }
    return this;
  }

  /**
   * Export to PDF
   *
   * @return
   * @throws DRException
   */
  public TemplateExporterBuilderService toPDF() throws DRException {
    JasperPdfExporterBuilder pdfExporterBuilder = export.pdfExporter(getTemplatePath().replace(extensionPattern, "pdf"));
    reportBuilder.toPdf(pdfExporterBuilder);
    return this;
  }

  /**
   * Export to XML
   *
   * @return
   * @throws DRException
   */
  public TemplateExporterBuilderService toXML() throws DRException {
    JasperXmlExporterBuilder xmlExporterBuilder = export.xmlExporter(getTemplatePath().replace(extensionPattern, "xml"));
    reportBuilder.toXml(xmlExporterBuilder);
    return this;
  }

  /**
   * Export to HTML
   *
   * @return
   * @throws DRException
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
   * @return
   * @throws DRException
   */
  public TemplateExporterBuilderService toCsv() throws DRException {
    JasperCsvExporterBuilder csvExporter = export.csvExporter(getTemplatePath().replace(extensionPattern, "csv"));
    reportBuilder.toCsv(csvExporter);
    return this;
  }

  /**
   * Export to DOCX
   *
   * @return
   * @throws DRException
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
   * @return
   * @throws DRException
   */
  public TemplateExporterBuilderService toExcel() throws DRException {
    return toXlsx();
  }

  /**
   * Export to ODS
   *
   * @return
   * @throws DRException
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
   * @return
   */
  public TemplateExporterBuilderService toPng() {
    return this;
  }

  /**
   * Export to RTF
   *
   * @return
   * @throws DRException
   */
  public TemplateExporterBuilderService toRtf() throws DRException {
    JasperRtfExporterBuilder rtfExporterBuilder = export.rtfExporter(getTemplatePath().replace(extensionPattern, "rtf"));
    reportBuilder.toRtf(rtfExporterBuilder);
    return this;
  }

  /**
   * Export to TXT
   *
   * @return
   * @throws DRException
   */
  public TemplateExporterBuilderService toText() throws DRException {
    JasperTextExporterBuilder textExporter = export.textExporter(getTemplatePath().replace(extensionPattern, "txt"));
    reportBuilder.toText(textExporter);
    return this;
  }

  /**
   * Export to XLS
   *
   * @return
   * @throws DRException
   */
  public TemplateExporterBuilderService toXls() throws DRException {
    JasperXlsExporterBuilder xlsExporter = export.xlsExporter(getTemplatePath().replace(extensionPattern, "xls"))
            .setIgnorePageMargins(true)
            .setWhitePageBackground(false)
            .setRemoveEmptySpaceBetweenRows(true)
            .setRemoveEmptySpaceBetweenColumns(true)
            .setFontSizeFixEnabled(false)
            .setImageBorderFixEnabled(true)
            .setDetectCellType(true)
            .setWrapText(false);

    // Remove headers and footers
    reportBuilder
            .setPageHeaderPrintWhenExpression(exp.value(Boolean.FALSE))
            .setPageFooterPrintWhenExpression(exp.value(Boolean.FALSE))
            .ignorePageWidth()
            .ignorePagination()
            .toXls(xlsExporter);
    return this;
  }

  /**
   * Export to XLSX
   *
   * @return
   * @throws DRException
   */
  public TemplateExporterBuilderService toXlsx() throws DRException {
    JasperXlsxExporterBuilder xlsExporter = export.xlsxExporter(getTemplatePath().replace(extensionPattern, "xlsx"))
            .setIgnorePageMargins(true)
            .setWhitePageBackground(false)
            .setRemoveEmptySpaceBetweenRows(true)
            .setRemoveEmptySpaceBetweenColumns(true)
            .setFontSizeFixEnabled(false)
            .setImageBorderFixEnabled(true)
            .setDetectCellType(true)
            .setWrapText(false);

    // Remove headers and footers
    reportBuilder
            .setPageHeaderPrintWhenExpression(exp.value(Boolean.FALSE))
            .setPageFooterPrintWhenExpression(exp.value(Boolean.FALSE))
            .ignorePageWidth()
            .ignorePagination()
            .toXlsx(xlsExporter);
    return this;
  }

  /**
   * Show generated report
   *
   * @return
   */
  public TemplateExporterBuilderService show() {
    try {
      this.reportBuilder
              .show();
    } catch (DRException exc) {
      logger.error("Error showing output", exc);
    }
    return this;
  }

  /**
   * Show generated JRXML
   *
   * @return
   */
  public TemplateExporterBuilderService showJrxml() {
    try {
      this.reportBuilder
              .showJrXml();
    } catch (DRException exc) {
      logger.error("Error showing JRXML", exc);
    }
    return this;
  }

  /**
   * Export to PDF Stream
   *
   * @param outputStream
   * @return
   * @throws DRException
   */
  public TemplateExporterBuilderService toPDFStream(OutputStream outputStream) throws DRException {
    reportBuilder.setDataSource(getDataSource());
    reportBuilder.toPdf(outputStream);
    return this;
  }
}
