package com.almis.ade.api.bean.input;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.net.URL;

/**
 * SpecificPrintBean class
 * @author dfuentes
 */
@XmlRootElement (name="specificPrintBean")
@JsonInclude (JsonInclude.Include.NON_NULL)
public class SpecificPrintBean {
  private File templateFile;
  private URL templateURL;

  /**
   * Get file template
   * @return File
   */
  public File getTemplateFile() {
    return templateFile;
  }

  /**
   * Set template file
   * @param templateFile template file
   * @return SpecificPrintBean
   */
  public SpecificPrintBean setTemplateFile(File templateFile) {
    this.templateFile = templateFile;
    return this;
  }

  /**
   * Get template URL
   * @return URL
   */
  public URL getTemplateURL() {
    return templateURL;
  }

  /**
   * Set template URL
   * @param templateURL template URL
   * @return SpecificPrintBean
   */
  public SpecificPrintBean setTemplateURL(URL templateURL) {
    this.templateURL = templateURL;
    return this;
  }
}
