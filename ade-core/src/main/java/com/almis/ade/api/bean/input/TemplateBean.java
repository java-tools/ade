package com.almis.ade.api.bean.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Map;

/**
 * SpecificPrintBean class
 * @author dfuentes
 */
@Data
@Accessors(chain = true)
@JsonInclude (JsonInclude.Include.NON_NULL)
public class TemplateBean {
  // JasperPrint generation
  private Collection data;
  private Map<String, Object> parameters;
  private String templatePath;
  private String templateName;
}
