package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.input.PrintBean;
import com.almis.ade.api.engine.jasper.generation.builder.mapper.BuilderMapper;
import com.almis.ade.api.interfaces.IBuilderInitializer;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

import javax.validation.constraints.NotNull;

/**
 *
 * @author dfuentes
 * @param <T>
 * @param <D>
 */
public abstract class ElementBuilder<T, D> implements IBuilderInitializer {
  private PrintBean printBean;

  /**
   *
   */
  protected String identifier;

  /**
   *
   */
  public ElementBuilder(){
    initialize();
  }

  /**
   *
   * @return
   */
  public BuilderMapper getBuilderMapper() {
    return BuilderMapper.getInstance();
  }

  /**
   *
   * @return
   */
  public String getIdentifier() {
    return identifier;
  }

  /**
   *
   * @param identifier
   * @return
   */
  public ElementBuilder setIdentifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  /**
   * Overridable method to initialize any element on it's instantiation.
   *
   */
  @Override
  public void initialize() {}

  //Jasper builder

  /**
   *
   * @param element
   * @param reportBuilder
   * @return
   */
  public abstract D build(@NotNull T element, JasperReportBuilder reportBuilder);

  /**
   *
   * @return
   */
  public PrintBean getPrintBean() {
    return printBean;
  }

  /**
   *
   * @param printBean
   * @return
   */
  public ElementBuilder<T, D> setPrintBean(PrintBean printBean) {
    this.printBean = printBean;
    return this;
  }
}
