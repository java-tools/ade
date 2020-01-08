package com.almis.ade.api.bean.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * SpecificPrintBean class
 * @author dfuentes
 */
@Data
@Accessors(chain = true)
@JsonInclude (JsonInclude.Include.NON_NULL)
public class ReportBean {
  // Report generation
  private List<TemplateBean> reportList;
  private List<String> reportFormats;
  private String reportName;
  private String reportPath;
  private String reportPassword;
}
