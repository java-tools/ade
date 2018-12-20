package com.almis.ade.api.bean.input;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.net.URL;

/**
 *
 * @author dfuentes
 */
@XmlRootElement (name="specificPrintBean")
@JsonInclude (JsonInclude.Include.NON_NULL)
public class SpecificPrintBean {
  private File templateFile;
  private URL templateURL;

  /**
   *
   * @return
   */
  public File getTemplateFile() {
    return templateFile;
  }

  /**
   *
   * @param templateFile
   * @return
   */
  public SpecificPrintBean setTemplateFile(File templateFile) {
    this.templateFile = templateFile;
    return this;
  }

  /**
   *
   * @return
   */
  public URL getTemplateURL() {
    return templateURL;
  }

  /**
   *
   * @param templateURL
   * @return
   */
  public SpecificPrintBean setTemplateURL(URL templateURL) {
    this.templateURL = templateURL;
    return this;
  }
}
