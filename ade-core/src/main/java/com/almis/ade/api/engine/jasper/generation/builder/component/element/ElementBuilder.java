package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.input.PrintBean;
import com.almis.ade.api.engine.jasper.generation.builder.mapper.BuilderMapper;
import com.almis.ade.api.interfaces.IBuilderInitializer;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

import javax.validation.constraints.NotNull;

/**
 * ElementBuilder class
 * @author dfuentes
 *
 * @param <T> Object
 * @param <D> Object
 */
public abstract class ElementBuilder<T, D> implements IBuilderInitializer {

  private PrintBean printBean;
  protected String identifier;

  /**
   * ElementBuilder constructor
   */
  public ElementBuilder(){
    initialize();
  }

  /**
   * Get BuilderMapper
   * @return BuilderMapper
   */
  public BuilderMapper getBuilderMapper() {
    return BuilderMapper.getInstance();
  }

  /**
   * Get element identifier
   * @return element identifier
   */
  public String getIdentifier() {
    return identifier;
  }

  /**
   * Set element identifier
   * @param identifier element identifier
   * @return ElementBuilder
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
   * Jasper build
   * @param element element
   * @param reportBuilder report builder
   * @return Object
   */
  public abstract D build(@NotNull T element, JasperReportBuilder reportBuilder);

  /**
   * Get print bean
   * @return PrintBean
   */
  public PrintBean getPrintBean() {
    return printBean;
  }

  /**
   * Set print bean
   * @param printBean print bean
   * @return ElementBuilder
   */
  public ElementBuilder<T, D> setPrintBean(PrintBean printBean) {
    this.printBean = printBean;
    return this;
  }
}
