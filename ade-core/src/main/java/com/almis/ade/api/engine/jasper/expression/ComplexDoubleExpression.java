package com.almis.ade.api.engine.jasper.expression;

import com.almis.ade.api.bean.input.DataBean;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Component expression to evaluate component values in columns
 */
public class ComplexDoubleExpression extends AbstractSimpleExpression<BigDecimal> {
  private static final long serialVersionUID = 1L;
  private final String field;

  /**
   * Complex expression constructor
   *
   * @param field Field to evaluate
   */
  public ComplexDoubleExpression(String field) {
    this.field = field;
  }

  /**
   * Evaluate expression
   *
   * @param reportParameters Report parameters
   * @return Expression value
   */
  @Override
  public BigDecimal evaluate(ReportParameters reportParameters) {
    DataBean data = reportParameters.getValue(field);
    return BigDecimal.valueOf(Double.valueOf(Optional
      .ofNullable((String) data.getSingleValue("label"))
      .orElse((String) data.getSingleValue("value"))));
  }
}