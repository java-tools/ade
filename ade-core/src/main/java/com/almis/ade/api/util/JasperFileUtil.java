package com.almis.ade.api.util;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRPrintXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

/**
 *
 * @author dfuentes
 */
public class JasperFileUtil {

  /**
   * Private constructor to hide the implicit one
   */
  private JasperFileUtil() {}

  /**
   * Export file to .jrpxml file
   *
   * @param report report builder
   * @param out file
   * @throws DRException DRException exception
   * @throws JRException JRException exception
   */
  public static void exportReportToJRPXMLFile(JasperReportBuilder report, OutputStream out) throws DRException, JRException {

    JasperPrint jp = report.toJasperPrint();

    JasperExportManager.exportReportToXmlStream(jp, out);
  }

  /**
   * Export file to .jrxml file
   *
   * @param jasperDesign jasper design
   * @param out path
   * @throws JRException JRException exception
   */
  public static void exportReportToJRXMLFile(JasperDesign jasperDesign, Path out) throws JRException {

    JasperCompileManager.compileReportToFile(jasperDesign, out.toFile().getAbsolutePath());
  }

  /**
   * Export file to .jrxml file
   *
   * @param report report builder
   * @param out File output stream
   * @throws DRException DRException exception
   */
  public static void exportReportToJRXMLFile(JasperReportBuilder report, OutputStream out) throws DRException {
    report.toJrXml(out);
  }

  /**
   * Export file to .jasper file
   *
   * @param report  report builder
   * @param out ile output stream
   * @throws JRException JRException exception
   * @throws DRException DRException exception
   */
  public static void exportReportToJasperFile(JasperReportBuilder report, OutputStream out) throws JRException, DRException {
    JasperCompileManager.compileReportToStream(report.toJasperDesign(), out);
  }

  /**
   * Load JasperPrint from .jrpxml file
   *
   * @param inputStream File input stream
   * @return JasperPrint
   * @throws JRException JRException exception
   */
  public static JasperPrint loadFromJRPXMLFile(InputStream inputStream) throws JRException {
    return JRPrintXmlLoader.load(inputStream);
  }

  /**
   * Load JasperDesign from .jrxml file
   *
   * @param inputStream File input stream
   * @return JasperDesign
   * @throws JRException JRException exception
   */
  public static JasperDesign loadFromJRXMLFile(InputStream inputStream) throws JRException {
    return JRXmlLoader.load(inputStream);
  }

  /**
   * Load JasperReport from .jasper file
   *
   * @param inputStream File input stream
   * @return JasperReport
   * @throws JRException JRException exception
   */
  public static JasperReport loadFromJasperFile(InputStream inputStream) throws JRException {
    return (JasperReport)JRLoader.loadObject(inputStream);
  }
}
