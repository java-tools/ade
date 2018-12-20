package com.almis.ade.api.fluid.engine.specific;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * @author dfuentes
 */
public class SpecificTemplateExporterBuilderService {
  @Value ("${document.extension.pattern:{extension}}")
  private String extensionPattern;

  private JasperPrint report;
  private String defaultPath;
  private String defaultName;
  private String token;

  /**
   * Initialize class
   *
   * @param report
   * @param path
   * @param name
   * @param token
   * @return
   */
  public SpecificTemplateExporterBuilderService initialize(JasperPrint report, String path, String name, String token) {
    this.report = report;
    this.defaultPath = path;
    this.defaultName = name;
    this.token = token;
    return this;
  }

  /**
   * Get formatted template path
   *
   * @return
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
   * @return
   * @throws JRException
   */
  public SpecificTemplateExporterBuilderService toPDF() throws JRException {
    JasperExportManager.exportReportToPdfFile(this.report, getTemplatePath().replace(extensionPattern, "pdf"));
    return this;
  }

  /**
   * Export to XML
   *
   * @return
   * @throws JRException
   * @throws FileNotFoundException
   */
  public SpecificTemplateExporterBuilderService toXML() throws JRException, FileNotFoundException {
    JasperExportManager.exportReportToXmlStream(this.report, new FileOutputStream(new File(getTemplatePath().replace(extensionPattern, "xml"))));
    return this;
  }

  /**
   * Export to HTML
   *
   * @return
   * @throws JRException
   */
  public SpecificTemplateExporterBuilderService toHTML() throws JRException {
    JasperExportManager.exportReportToHtmlFile(this.report, getTemplatePath().replace(extensionPattern, "html"));
    return this;
  }

  /**
   * Export to CSV
   *
   * @return
   * @throws JRException
   */
  public SpecificTemplateExporterBuilderService toCsv() throws JRException {
    JRCsvExporter jasperCsvExporter = new JRCsvExporter();
    jasperCsvExporter.setExporterInput(new SimpleExporterInput(this.report));
    jasperCsvExporter.setExporterOutput(new SimpleWriterExporterOutput(new File(getTemplatePath().replace(extensionPattern, "csv"))));
    jasperCsvExporter.exportReport();
    return this;
  }

  /**
   * Export to DOCX
   *
   * @return
   * @throws JRException
   */
  public SpecificTemplateExporterBuilderService toDocx() throws JRException {
    JRDocxExporter jrDocxExporter = new JRDocxExporter();
    jrDocxExporter.setExporterInput(new SimpleExporterInput(this.report));
    jrDocxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(getTemplatePath().replace(extensionPattern, "docx"))));
    jrDocxExporter.exportReport();
    return this;
  }

  /**
   * Export to EXCEL
   *
   * @return
   * @throws JRException
   */
  public SpecificTemplateExporterBuilderService toExcel() throws JRException {
    JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
    jrXlsxExporter.setExporterInput(new SimpleExporterInput(this.report));
    jrXlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(getTemplatePath().replace(extensionPattern, "xlsx"))));
    jrXlsxExporter.exportReport();
    return this;
  }

  /**
   * Export to ODS
   *
   * @return
   * @throws JRException
   */
  public SpecificTemplateExporterBuilderService toOds() throws JRException {
    JROdsExporter jrOdsExporter = new JROdsExporter();
    jrOdsExporter.setExporterInput(new SimpleExporterInput(this.report));
    jrOdsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(getTemplatePath().replace(extensionPattern, "ods"))));
    jrOdsExporter.exportReport();
    return this;
  }

  /**
   * Export to PNG
   *
   * @return
   * @throws JRException
   * @throws java.io.IOException
   */
  public SpecificTemplateExporterBuilderService toPng() throws JRException, IOException {
    BufferedImage pageImage = new BufferedImage(this.report.getPageWidth() + 1, this.report.getPageHeight() + 1, BufferedImage.TYPE_INT_RGB);
    JRGraphics2DExporter exporter = new JRGraphics2DExporter();
    exporter.setExporterInput(new SimpleExporterInput(this.report));
    SimpleGraphics2DExporterOutput exporterOutput = new SimpleGraphics2DExporterOutput();
    exporterOutput.setGraphics2D((Graphics2D) pageImage.getGraphics());
    exporter.setExporterOutput(exporterOutput);
    exporter.exportReport();
    ImageIO.write(pageImage, "png", new FileOutputStream(new File(getTemplatePath().replace(extensionPattern, "png"))));
    return this;
  }

  /**
   * Export to RTF
   *
   * @return
   * @throws JRException
   */
  public SpecificTemplateExporterBuilderService toRtf() throws JRException {
    JRRtfExporter jrRtfExporter = new JRRtfExporter();
    jrRtfExporter.setExporterInput(new SimpleExporterInput(this.report));
    jrRtfExporter.setExporterOutput(new SimpleWriterExporterOutput(new File(getTemplatePath().replace(extensionPattern, "rtf"))));
    jrRtfExporter.exportReport();
    return this;
  }

  /**
   * Export to TXT
   *
   * @return
   * @throws JRException
   */
  public SpecificTemplateExporterBuilderService toText() throws JRException {
    JRTextExporter jrTextExporter = new JRTextExporter();
    jrTextExporter.setExporterInput(new SimpleExporterInput(this.report));
    jrTextExporter.setExporterOutput(new SimpleWriterExporterOutput(new File(getTemplatePath().replace(extensionPattern, "txt"))));
    jrTextExporter.exportReport();
    return this;
  }

  /**
   * Export to XHTML
   *
   * @return
   * @throws JRException
   */
  public SpecificTemplateExporterBuilderService toXhtml() throws JRException {
    HtmlExporter htmlExporter = new HtmlExporter();
    htmlExporter.setExporterInput(new SimpleExporterInput(this.report));
    htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(new File(getTemplatePath().replace(extensionPattern, "html"))));
    htmlExporter.exportReport();
    return this;
  }

  /**
   * Export to XLS
   *
   * @return
   * @throws JRException
   */
  public SpecificTemplateExporterBuilderService toXls() throws JRException {
    JRXlsExporter jrXlsExporter = new JRXlsExporter();
    jrXlsExporter.setExporterInput(new SimpleExporterInput(this.report));
    jrXlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(getTemplatePath().replace(extensionPattern, "xls"))));
    jrXlsExporter.exportReport();
    return this;
  }

  /**
   * Export to XLSX
   *
   * @return
   * @throws JRException
   */
  public SpecificTemplateExporterBuilderService toXlsx() throws JRException {
    JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
    jrXlsxExporter.setExporterInput(new SimpleExporterInput(this.report));
    jrXlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(getTemplatePath().replace(extensionPattern, "xls"))));
    jrXlsxExporter.exportReport();
    return this;
  }

  /**
   * Get file token
   *
   * @return
   */
  public String getToken() {
    return token;
  }
}