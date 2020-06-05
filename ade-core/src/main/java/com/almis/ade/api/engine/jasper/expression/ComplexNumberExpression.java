package com.almis.ade.api.engine.jasper.expression;

import com.almis.ade.api.bean.input.DataBean;
import com.almis.ade.api.enumerate.ColumnType;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.definition.ReportParameters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

/**
 * Component expression to evaluate component values in columns
 */
public class ComplexNumberExpression extends AbstractSimpleExpression<Object> {
  private static final long serialVersionUID = 1L;
  private final String field;
  private final ColumnType type;

  /**
   * Complex expression constructor
   *
   * @param field Field to evaluate
   * @param type  Column type
   */
  public ComplexNumberExpression(String field, ColumnType type) {
    this.field = field;
    this.type = type;
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
      String value = (String) data.getSingleValue("value");
      switch (type) {
        case INTEGER:
          return Integer.parseInt(value);
        case LONG:
          return Long.parseLong(value);
        case BIGINTEGER:
          return BigInteger.valueOf(Long.parseLong(value));
        case FLOAT:
          return Float.parseFloat(value);
        case DOUBLE:
          return Double.parseDouble(value);
        case BIGDECIMAL:
          return BigDecimal.valueOf(Double.parseDouble(value));
        default:
          return value;
      }
    } else {
      return Optional
        .ofNullable((String) data.getSingleValue("label"))
        .orElse((String) data.getSingleValue("value"));
    }
  }
}