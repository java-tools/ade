package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.Barcode;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

import javax.validation.constraints.NotNull;

/**
 *
 * @author dfuentes
 */
public class BarcodeBuilder extends ElementBuilder<Barcode, ComponentBuilder> {

  /**
   *
   * @param element barcode element
   * @param jasperReportBuilder jasper report builder
   * @return ComponentBuilder
   */
  @Override
  public ComponentBuilder build(@NotNull Barcode element, JasperReportBuilder jasperReportBuilder) {
    return element.getBarcodeType().getBarcode(element.getCode()).setStyle(element.getStyle())/*
            .setDrawText(element.isDrawText())
            .setBarHeight(element.getSize())
            .setBarWidth(element.getSize() * 3)*/;
  }
}
