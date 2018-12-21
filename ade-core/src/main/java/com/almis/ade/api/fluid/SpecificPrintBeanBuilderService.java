package com.almis.ade.api.fluid;

import com.almis.ade.api.bean.input.SpecificPrintBean;
import com.almis.ade.api.fluid.engine.specific.SpecificTemplateDataBuilderService;
import com.almis.ade.api.util.FileUtil;
import com.almis.ade.api.util.JasperFileUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * SpecificPrintBeanBuilderService class
 * @author dfuentes
 */
public class SpecificPrintBeanBuilderService {

  // Autowired service
  private SpecificTemplateDataBuilderService specificTemplateDataBuilderService;
  private SpecificPrintBean specificPrintBean;

  /**
   * Autowired constructor
   * @param specificTemplateDataBuilderService specific template of data builder
   */
  @Autowired
  public SpecificPrintBeanBuilderService(SpecificTemplateDataBuilderService specificTemplateDataBuilderService) {
    this.specificTemplateDataBuilderService = specificTemplateDataBuilderService;
  }

  /**
   * Initialize class
   *
   * @param specificPrintBean specificPrintBean
   * @return SpecificPrintBeanBuilderService
   */
  public SpecificPrintBeanBuilderService initialize(SpecificPrintBean specificPrintBean) {
    return setSpecificPrintBean(specificPrintBean);
  }

  /**
   * Get specific print bean
   *
   * @return SpecificPrintBean
   */
  public SpecificPrintBean getSpecificPrintBean() {
    return specificPrintBean;
  }

  /**
   * Set specific print bean
   *
   * @param specificPrintBean specificPrintBean
   * @return SpecificPrintBeanBuilderService
   */
  public SpecificPrintBeanBuilderService setSpecificPrintBean(SpecificPrintBean specificPrintBean) {
    this.specificPrintBean = specificPrintBean;
    return this;
  }

  /**
   * Build report and set data to export
   *
   * @return SpecificTemplateDataBuilderService
   * @throws FileNotFoundException FileNotFoundException exception
   * @throws JRException JRException exception
   */
  public SpecificTemplateDataBuilderService build() throws FileNotFoundException, JRException {
    JasperDesign jasperPrint = JasperFileUtil.loadFromJRXMLFile(getFile());

    JasperReport compiledReport = JasperCompileManager.compileReport(jasperPrint);

    return specificTemplateDataBuilderService.initialize(compiledReport, null);
  }

  /**
   * Get File from specific report bean
   *
   * @return InputStream
   * @throws FileNotFoundException FileNotFoundException exception
   */
  private InputStream getFile() throws FileNotFoundException {
    if(specificPrintBean.getTemplateFile() != null){
      return new FileInputStream(specificPrintBean.getTemplateFile());
    }else{
      if(specificPrintBean.getTemplateURL() != null){
        InputStream files = FileUtil.getFileFromURL(specificPrintBean.getTemplateURL());
        if(files != null){
          return files;
        }
      }
    }
    return null;
  }
}
