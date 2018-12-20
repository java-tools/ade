package com.almis.ade.controller;

import com.almis.ade.api.bean.input.SpecificPrintBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dfuentes
 */
@RestController
@RequestMapping("/engine/generate")
public class FileGeneratorController {

  /**
   * Generate a generic report
   */
  @RequestMapping("/generic")
  public void printGenericReport(){
    throw new UnsupportedOperationException();
  }

  /**
   * Generate a specific report
   * @param specificPrintBean
   * @return
   */
  @RequestMapping(path = "/specific", produces = "application/json")
  public SpecificPrintBean printSpecificReport(@RequestParam ("printBean") SpecificPrintBean specificPrintBean){
    return specificPrintBean;
  }
}
