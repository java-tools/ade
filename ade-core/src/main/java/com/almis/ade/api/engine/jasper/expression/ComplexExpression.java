package com.almis.ade.api.engine.jasper.expression;

import com.almis.ade.api.bean.input.DataBean;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

import java.util.Optional;

/**
 * Component expression to evaluate component values in columns
 */
public class ComplexExpression extends AbstractSimpleExpression<String> {
  private static final long serialVersionUID = 1L;
  private final String field;

  /**
   * Complex expression constructor
   * @param field Field to evaluate
   */
  public ComplexExpression(String field) {
    this.field = field;
  }

  /**
   * Evaluate expression
   * @param reportParameters Report parameters
   * @return Expression value
   */
  @Override
  public String evaluate(ReportParameters reportParameters) {
    DataBean data = reportParameters.getValue(field);
    return Optional
      .ofNullable(String.valueOf(data.getSingleValue("label")))
      .orElse(String.valueOf(data.getSingleValue("value")));
  }
}