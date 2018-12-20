package com.almis.ade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dfuentes
 */
@RestController
@RequestMapping("/engine")
public class PrintEngineController {

  /**
   *
   * @return
   */
  @RequestMapping("/")
  public String engineGuide(){
    return "This is the ADE document generator";
  }

}
