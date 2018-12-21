package com.almis.ade.api.bean.component.grid;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.bean.component.Text;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dfuentes
 */
public class ReportGrid extends Element<ReportGrid> {
  private List<GridHeader> gridHeaders;
  private ReportColumn styleColumn;
  private List<String> fields;
  private List<List<Object>> data;
  private Text title;
  private PageOrientation orientation;
  private Integer fontSize;

  /**
   * Constructor
   *
   * @param identifier ReportGrid identifier
   */
  public ReportGrid(@NotNull String identifier) {
    super(identifier);
    this.gridHeaders = new ArrayList<>();
    this.setStyleColumn(null);
    this.fontSize = null;
  }

  /**
   * Get title
   * @return title
   */
  public Text getTitle() {
    return title;
  }

  /**
   * Set title
   * @param title Title
   * @return this
   */
  public ReportGrid setTitle(Text title) {
    this.title = title;
    return this;
  }

  /**
   * Add grid header
   * @param gridHeader ReportGrid header
   */
  public void addGridHeader(GridHeader gridHeader){
    this.gridHeaders.add(gridHeader);
  }

  /**
   * Get column list
   * @return ReportColumn list
   */
  public List<GridHeader> getGridHeaders() {
    return gridHeaders;
  }

  /**
   * Set grid fields
   * @param fields ReportGrid fields
   * @return this
   */
  @SuppressWarnings("unchecked")
  public ReportGrid setFields(List fields){
    this.fields = fields;
    return this;
  }

  /**
   * Set grid data
   * @param data ReportGrid data
   * @return this
   */
  @SuppressWarnings("unchecked")
  public ReportGrid setData(List data){
    this.data = data;
    return this;
  }

  /**
   * Get grid data
   * @return ReportGrid data
   */
  public JRDataSource getDataSource() {
    if (fields == null || data == null || data.isEmpty()) {
      return new JREmptyDataSource();
    }

    // Generate datasource fields
    DRDataSource dataSource = new DRDataSource(fields.toArray(new String[fields.size()]));
    for (List<Object> row : data) {
      // Fill datasource with data
      dataSource.add(row.toArray(new Object[row.size()]));
    }

    return dataSource;
  }

  /**
   * Get grid orientation
   * @return PageOrientation
   */
  public PageOrientation getOrientation() {
    return orientation;
  }

  /**
   * Set grid orientation
   * @param orientation orientation
   * @return ReportGrid
   */
  public ReportGrid setOrientation(PageOrientation orientation) {
    this.orientation = orientation;
    return this;
  }

  /**
   * Get report font size
   * @return Font size
   */
  public Integer getFontSize() {
    return fontSize;
  }

  /**
   * Set report font size
   * @param fontSize Font size
   * @return this
   */
  public ReportGrid setFontSize(Integer fontSize) {
    this.fontSize = fontSize;
    return this;
  }

  /**
   * Retrieve style column
   * @return Style column
   */
  public ReportColumn getStyleColumn() {
    return styleColumn;
  }

  /**
   * Set style column
   * @param styleColumn Style column
   * @return this
   */
  public ReportGrid setStyleColumn(ReportColumn styleColumn) {
    this.styleColumn = styleColumn;
    return this;
  }
}
