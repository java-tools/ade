package com.almis.ade.controller;

import com.almis.ade.api.bean.input.TemplateBean;
import org.springframework.web.bind.annotation.*;

/**
 * FileGeneratorController class
 * @author dfuentes
 */
@RestController
@RequestMapping("/engine/generate")
public class FileGeneratorController {

  /**
   * Generate a generic report
   */
  @PostMapping(value = "/generic")
  public void printGenericReport(){
    throw new UnsupportedOperationException();
  }

  /**
   * Generate a specific report
   * @param templateBean print bean
   * @return SpecificPrintBean
   */
  @PostMapping(path = "/specific", produces = "application/json")
  public TemplateBean printSpecificReport(@RequestParam ("printBean") TemplateBean templateBean){
    return templateBean;
  }
}
