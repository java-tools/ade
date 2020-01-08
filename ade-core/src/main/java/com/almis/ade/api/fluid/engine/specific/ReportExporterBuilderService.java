package com.almis.ade.api.fluid.engine.specific;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author dfuentes
 */
public class ReportExporterBuilderService {
  @Value ("${document.extension.pattern:{extension}}")
  private String extensionPattern;

  private List<JasperPrint> reportList;
  private String defaultPath;
  private String defaultName;
  private String filePassword;

  /**
   * Initialize class
   *
   * @param reportList JasperPrint report list
   * @param path report path
   * @param name report name
   * @return SpecificTemplateExporterBuilderService
   */
  public ReportExporterBuilderService initialize(List<JasperPrint> reportList, String path, String name, String password) {
    this.reportList = reportList;
    this.defaultPath = path;
    this.defaultName = name;
    this.filePassword = password;
    return this;
  }

  /**
   * Get formatted template path
   *
   * @return template path
   */
  public String getTemplatePath() {
    this.defaultPath = defaultPath.endsWith(File.separator) ? defaultPath : defaultPath + File.separator;

    if (Paths.get(defaultPath).toFile().mkdirs()) {
      //Throw exception, couldn't create directories
    }

    return defaultPath + defaultName + "." + extensionPattern;
  }

  /**
   * Export to PDF
   *
   * @return SpecificTemplateExporterBuilderService
   * @throws JRException JRException exception
   */
  public ReportExporterBuilderService toPdf() throws JRException {
    JRPdfExporter exporter = new JRPdfExporter();
    exporter.setExporterInput(SimpleExporterInput.getInstance(reportList));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(getTemplatePath().replace(extensionPattern, "pdf"))));

    // Add password if defined
    if (filePassword != null) {
      SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
      configuration.setEncrypted(true);
      configuration.setUserPassword(filePassword);
      configuration.setOwnerPassword(filePassword);
      exporter.setConfiguration(configuration);
    }

    exporter.exportReport();
    return this;
  }

  /**
   * Export to XML
   *
   * @return SpecificTemplateExporterBuilderService
   * @throws JRException JRException exception
   */
  public ReportExporterBuilderService toXml() throws JRException {
    JRXmlExporter exporter = new JRXmlExporter();
    exporter.setExporterInput(SimpleExporterInput.getInstance(reportList));
    exporter.setExporterOutput(new SimpleXmlExporterOutput(new File(getTemplatePath().replace(extensionPattern, "xml"))));
    exporter.exportReport();
    return this;
  }

  /**
   * Export to HTML
   *
   * @return SpecificTemplateExporterBuilderService
   * @throws JRException JRException exception
   */
  public ReportExporterBuilderService toHtml() throws JRException {
    HtmlExporter exporter = new HtmlExporter();
    exporter.setExporterInput(SimpleExporterInput.getInstance(reportList));
    exporter.setExporterOutput(new SimpleHtmlExporterOutput(new File(getTemplatePath().replace(extensionPattern, "html"))));
    exporter.exportReport();
    return this;
  }

  /**
   * Export to CSV
   *
   * @return SpecificTemplateExporterBuilderService
   * @throws JRException JRException exception
   */
  public ReportExporterBuilderService toCsv() throws JRException {
    JRCsvExporter exporter = new JRCsvExporter();
    exporter.setExporterInput(SimpleExporterInput.getInstance(reportList));
    exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(getTemplatePath().replace(extensionPattern, "csv"))));
    exporter.exportReport();
    return this;
  }

  /**
   * Export to DOCX
   *
   * @return SpecificTemplateExporterBuilderService
   * @throws JRException JRException exception
   */
  public ReportExporterBuilderService toDocx() throws JRException {
    JRDocxExporter exporter = new JRDocxExporter();
    exporter.setExporterInput(SimpleExporterInput.getInstance(reportList));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(getTemplatePath().replace(extensionPattern, "docx"))));
    exporter.exportReport();
    return this;
  }

  /**
   * Export to EXCEL
   *
   * @return SpecificTemplateExporterBuilderService
   * @throws JRException JRException exception
   */
  public ReportExporterBuilderService toExcel() throws JRException {
    JRXlsxExporter exporter = new JRXlsxExporter();
    exporter.setExporterInput(SimpleExporterInput.getInstance(reportList));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(getTemplatePath().replace(extensionPattern, "xlsx"))));
    exporter.exportReport();
    return this;
  }

  /**
   * Export to ODS
   *
   * @return SpecificTemplateExporterBuilderService
   * @throws JRException JRException exception
   */
  public ReportExporterBuilderService toOds() throws JRException {
    JROdsExporter exporter = new JROdsExporter();
    exporter.setExporterInput(SimpleExporterInput.getInstance(reportList));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(getTemplatePath().replace(extensionPattern, "ods"))));
    exporter.exportReport();
    return this;
  }

  /**
   * Export to RTF
   *
   * @return SpecificTemplateExporterBuilderService
   * @throws JRException JRException exception
   */
  public ReportExporterBuilderService toRtf() throws JRException {
    JRRtfExporter exporter = new JRRtfExporter();
    exporter.setExporterInput(SimpleExporterInput.getInstance(reportList));
    exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(getTemplatePath().replace(extensionPattern, "rtf"))));
    exporter.exportReport();
    return this;
  }

  /**
   * Export to TXT
   *
   * @return SpecificTemplateExporterBuilderService
   * @throws JRException JRException exception
   */
  public ReportExporterBuilderService toText() throws JRException {
    JRTextExporter exporter = new JRTextExporter();
    exporter.setExporterInput(SimpleExporterInput.getInstance(reportList));
    exporter.setExporterOutput(new SimpleWriterExporterOutput(new File(getTemplatePath().replace(extensionPattern, "txt"))));
    exporter.exportReport();
    return this;
  }

  /**
   * Export to XLS
   *
   * @return SpecificTemplateExporterBuilderService
   * @throws JRException JRException exception
   */
  public ReportExporterBuilderService toXls() throws JRException {
    JRXlsExporter exporter = new JRXlsExporter();
    exporter.setExporterInput(SimpleExporterInput.getInstance(reportList));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(getTemplatePath().replace(extensionPattern, "xls"))));
    exporter.exportReport();
    return this;
  }

  /**
   * Export to XLSX
   *
   * @return SpecificTemplateExporterBuilderService
   * @throws JRException JRException exception
   */
  public ReportExporterBuilderService toXlsx() throws JRException {
    JRXlsxExporter exporter = new JRXlsxExporter();
    exporter.setExporterInput(SimpleExporterInput.getInstance(reportList));
    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(getTemplatePath().replace(extensionPattern, "xls"))));
    exporter.exportReport();
    return this;
  }
}