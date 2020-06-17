package com.almis.ade.api.engine.jasper.expression;

import com.almis.ade.api.bean.input.DataBean;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

import java.util.Optional;

/**
 * Component expression to evaluate component values in columns
 */
public class ComplexNumberExpression extends AbstractSimpleExpression<Object> {
  private static final long serialVersionUID = 1L;
  private final String field;

  /**
   * Complex expression constructor
   *
   * @param field Field to evaluate
   */
  public ComplexNumberExpression(String field) {
    this.field = field;
  }

  /**
   * Evaluate expression
   *
   * @param reportParameters Report parameters
   * @return Expression value
   */
  @Override
  public Object evaluate(ReportParameters reportParameters) {
    DataBean data = reportParameters.getValue(field);
    boolean isExcel = (boolean) Optional.ofNullable(reportParameters.getParameterValue("IS_IGNORE_PAGINATION")).orElse(false);
    if (isExcel) {
      return data.getSingleValue("value");
    } else {
      return Optional
        .ofNullable(data.getSingleValue("label"))
        .orElse(data.getSingleValue("value"));
    }
  }
}