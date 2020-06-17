package com.almis.ade.api.engine.jasper.expression;

import com.almis.ade.api.bean.input.DataBean;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

import java.util.Optional;

/**
 * Component expression to evaluate component values in columns
 */
public class ComplexBooleanExpression extends AbstractSimpleExpression<Boolean> {
  private static final long serialVersionUID = 1L;
  private final String field;

  /**
   * Complex expression constructor
   *
   * @param field Field to evaluate
   */
  public ComplexBooleanExpression(String field) {
    this.field = field;
  }

  /**
   * Evaluate expression
   *
   * @param reportParameters Report parameters
   * @return Expression value
   */
  @Override
  public Boolean evaluate(ReportParameters reportParameters) {
    DataBean data = reportParameters.getValue(field);
    return Optional.ofNullable((Boolean) data.getSingleValue("label")).orElse(false);
  }
}