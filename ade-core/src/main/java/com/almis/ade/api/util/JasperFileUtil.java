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
   * @param report
   * @param out
   * @throws DRException
   * @throws JRException
   */
  public static void exportReportToJRPXMLFile(JasperReportBuilder report, OutputStream out) throws DRException, JRException {

    JasperPrint jp = report.toJasperPrint();

    JasperExportManager.exportReportToXmlStream(jp, out);
  }

  /**
   * Export file to .jrxml file
   *
   * @param jasperDesign
   * @param out
   * @throws JRException
   */
  public static void exportReportToJRXMLFile(JasperDesign jasperDesign, Path out) throws JRException {

    JasperCompileManager.compileReportToFile(jasperDesign, out.toFile().getAbsolutePath());
  }

  /**
   * Export file to .jrxml file
   *
   * @param report
   * @param out
   * @throws DRException
   */
  public static void exportReportToJRXMLFile(JasperReportBuilder report, OutputStream out) throws DRException {

    report.toJrXml(out);
  }

  /**
   * Export file to .jasper file
   *
   * @param report
   * @param out
   * @throws JRException
   * @throws DRException
   */
  public static void exportReportToJasperFile(JasperReportBuilder report, OutputStream out) throws JRException, DRException {

    JasperCompileManager.compileReportToStream(report.toJasperDesign(), out);

  }

  /**
   * Load JasperPrint from .jrpxml file
   *
   * @param inputStream
   * @return
   * @throws JRException
   */
  public static JasperPrint loadFromJRPXMLFile(InputStream inputStream) throws JRException {

    return JRPrintXmlLoader.load(inputStream);
  }

  /**
   * Load JasperDesign from .jrxml file
   *
   * @param inputStream
   * @return
   * @throws JRException
   */
  public static JasperDesign loadFromJRXMLFile(InputStream inputStream) throws JRException {
    return JRXmlLoader.load(inputStream);
  }

  /**
   * Load JasperReport from .jasper file
   *
   * @param inputStream
   * @return
   * @throws JRException
   */
  public static JasperReport loadFromJasperFile(InputStream inputStream) throws JRException {
    return (JasperReport)JRLoader.loadObject(inputStream);
  }
}
