package com.almis.ade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Engine controller
 * @author dfuentes
 */
@RestController
@RequestMapping("/engine")
public class PrintEngineController {

  /**
   * Retrieve engine guide info
   * @return description
   */
  @RequestMapping("/")
  public String engineGuide(){
    return "This is the ADE document generator";
  }

}
