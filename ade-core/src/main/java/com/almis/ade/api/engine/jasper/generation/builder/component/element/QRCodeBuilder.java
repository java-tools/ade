package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.QRCode;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

import javax.validation.constraints.NotNull;

import static net.sf.dynamicreports.report.builder.DynamicReports.bcode;

/**
 * @author dfuentes
 */
public class QRCodeBuilder extends ElementBuilder<QRCode, ComponentBuilder> {

  /**
   * @param element
   * @param jasperReportBuilder
   *
   * @return
   */
  @Override
  public ComponentBuilder build(@NotNull QRCode element, JasperReportBuilder jasperReportBuilder) {
    return bcode.qrCode(element.getCode()).setStyle(element.getStyle())/*
            .setDrawText(element.isDrawText())
            .setBarHeight(element.getSize())
            .setBarWidth(element.getSize() * 3)*/;
  }
}
