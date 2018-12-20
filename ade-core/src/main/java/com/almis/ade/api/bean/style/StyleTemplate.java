package com.almis.ade.api.bean.style;

import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalImageAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;

import java.awt.*;
import java.util.Locale;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * Style templates
 */
public class StyleTemplate {

  public static final StyleBuilder ROOT_STYLE;
  public static final StyleBuilder BOLD_STYLE;
  public static final StyleBuilder ITALIC_STYLE;
  public static final StyleBuilder BOLD_CENTERED_STYLE;
  public static final StyleBuilder BOLD_12_CENTERED_STYLE;
  public static final StyleBuilder BOLD_18_CENTERED_STYLE;
  public static final StyleBuilder BOLD_22_CENTERED_STYLE;
  public static final StyleBuilder CRITERIA_TITLE_STYLE;
  public static final StyleBuilder CRITERIA_VALUE_STYLE;
  public static final StyleBuilder TITLE_STYLE;
  public static final StyleBuilder COLUMN_STYLE;
  public static final StyleBuilder COLUMN_TITLE_STYLE;
  public static final StyleBuilder GROUP_STYLE;
  public static final StyleBuilder COLUMN_DATA_STYLE;
  public static final StyleBuilder DETAIL_STYLE;
  public static final StyleBuilder SUBTOTAL_STYLE;

  public static final ReportTemplateBuilder REPORT_TEMPLATE;
  public static final CurrencyType CURRENCY_TYPE;
  public static final ComponentBuilder<?, ?> FOOTER_COMPONENT;

  static {
    ROOT_STYLE = stl.style().setPadding(2);
    BOLD_STYLE = stl.style(ROOT_STYLE).bold();
    ITALIC_STYLE = stl.style(ROOT_STYLE).italic();
    BOLD_CENTERED_STYLE = stl.style(BOLD_STYLE)
            .setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);
    BOLD_12_CENTERED_STYLE = stl.style(BOLD_CENTERED_STYLE)
            .setFontSize(12);
    BOLD_18_CENTERED_STYLE = stl.style(BOLD_CENTERED_STYLE)
            .setFontSize(18);
    BOLD_22_CENTERED_STYLE = stl.style(BOLD_CENTERED_STYLE)
            .setFontSize(22);
    CRITERIA_TITLE_STYLE = stl
            .style(BOLD_STYLE)
            .setLeftPadding(4)
            .setTextAlignment(HorizontalTextAlignment.LEFT, VerticalTextAlignment.TOP);
    CRITERIA_VALUE_STYLE = stl
            .style(ROOT_STYLE)
            .setTextAlignment(HorizontalTextAlignment.LEFT, VerticalTextAlignment.TOP);
    TITLE_STYLE = stl
            .style(BOLD_STYLE)
            .setTextAlignment(HorizontalTextAlignment.LEFT, VerticalTextAlignment.MIDDLE);
    COLUMN_STYLE = stl
            .style(ROOT_STYLE)
            .setVerticalTextAlignment(VerticalTextAlignment.MIDDLE);
    COLUMN_TITLE_STYLE = stl.style(COLUMN_STYLE)
            .setBorder(stl.pen1Point()
                    .setLineColor(new Color(212, 212, 212)))
            .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
            .setBackgroundColor(new Color(235, 235, 235))
            .bold();
    COLUMN_DATA_STYLE = stl.style(ROOT_STYLE)
            .setLeftBorder(stl.pen1Point()
                    .setLineColor(new Color(212, 212, 212)))
            .setVerticalImageAlignment(VerticalImageAlignment.MIDDLE);
    DETAIL_STYLE = stl.style()
            .setBorder(stl.pen1Point()
                    .setLineColor(new Color(212, 212, 212)));

    GROUP_STYLE = stl.style(BOLD_STYLE)
            .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
    SUBTOTAL_STYLE = stl.style(BOLD_STYLE)
            .setTopBorder(stl.pen1Point());

    StyleBuilder crosstabGroupStyle = stl.style(COLUMN_TITLE_STYLE);
    StyleBuilder crosstabGroupTotalStyle = stl.style(COLUMN_TITLE_STYLE)
            .setBackgroundColor(new Color(170, 170, 170));
    StyleBuilder crosstabGrandTotalStyle = stl.style(COLUMN_TITLE_STYLE)
            .setBackgroundColor(new Color(140, 140, 140));
    StyleBuilder crosstabCellStyle = stl.style(COLUMN_STYLE)
            .setBorder(stl.pen1Point());

    TableOfContentsCustomizerBuilder tableOfContentsCustomizer = tableOfContentsCustomizer()
            .setHeadingStyle(0, stl.style(ROOT_STYLE).bold());

    REPORT_TEMPLATE = template()
            .setLocale(Locale.ENGLISH)
            .setTitleStyle(TITLE_STYLE)
            .setColumnStyle(COLUMN_STYLE)
            .setColumnTitleStyle(COLUMN_TITLE_STYLE)
            .setGroupStyle(GROUP_STYLE)
            .setGroupTitleStyle(GROUP_STYLE)
            .setSubtotalStyle(SUBTOTAL_STYLE)
            .setDetailStyle(DETAIL_STYLE)
            //.highlightDetailEvenRows()
            //.crosstabHighlightEvenRows()
            .setCrosstabGroupStyle(crosstabGroupStyle)
            .setCrosstabGroupTotalStyle(crosstabGroupTotalStyle)
            .setCrosstabGrandTotalStyle(crosstabGrandTotalStyle)
            .setCrosstabCellStyle(crosstabCellStyle)
            .setTableOfContentsCustomizer(tableOfContentsCustomizer);

    CURRENCY_TYPE = new CurrencyType();
    FOOTER_COMPONENT = cmp.pageXofY()
            .setStyle(
                    stl.style(BOLD_CENTERED_STYLE)
                            .setTopBorder(stl.pen1Point()));
  }

  /**
   * Hide the constructor to avoid instantiation
   */
  private StyleTemplate(){}

  /**
   * Create currency value formatter
   * @param label Label
   * @return Formatter
   */
  public static CurrencyValueFormatter createCurrencyValueFormatter(String label) {
    return new CurrencyValueFormatter(label);
  }

  /**
   * Currency type
   */
  public static class CurrencyType extends BigDecimalType {
    private static final long serialVersionUID = 1L;

    @Override
    public String getPattern() {
      return "$ #,###.00";
    }
  }

  private static class CurrencyValueFormatter extends AbstractValueFormatter<String, Number> {
    private static final long serialVersionUID = 1L;

    private String label;

    /**
     * Constructor
     * @param label Label
     */
    public CurrencyValueFormatter(String label) {
      this.label = label;
    }

    @Override
    public String format(Number value, ReportParameters reportParameters) {
      return label + CURRENCY_TYPE.valueToString(value, reportParameters.getLocale());
    }
  }
}
