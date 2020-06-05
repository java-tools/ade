package com.almis.ade.api.engine.jasper.expression;

import com.almis.ade.api.bean.input.DataBean;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 * Component expression to evaluate component values in columns
 */
public class ComplexDateExpression extends AbstractSimpleExpression<Object> {
  private static final long serialVersionUID = 1L;
  private final String field;

  /**
   * Complex expression constructor
   *
   * @param field Field to evaluate
   */
  public ComplexDateExpression(String field) {
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
    String dateString = Optional.ofNullable((String) data.getSingleValue("label"))
      .orElse((String) data.getSingleValue("value"));
    if (isExcel) {
      try {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
      } catch (ParseException exc) {
        return dateString;
      }
    } else {
      return dateString;
    }
  }
}