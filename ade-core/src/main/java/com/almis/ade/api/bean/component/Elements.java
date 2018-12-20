package com.almis.ade.api.bean.component;

import com.almis.ade.api.bean.component.grid.ReportGrid;
import com.almis.ade.api.enumerate.BarcodeType;
import com.almis.ade.api.enumerate.LayoutType;
import com.almis.ade.api.enumerate.PagingType;
import net.sf.dynamicreports.report.constant.BreakType;

/**
 * ADE Element collection
 */
public class Elements {

  /**
   * Private constructor to hide implicit one
   */
  private Elements() {}

  /**
   * Grid
   * @param id Grid id
   * @return Report grid
   */
  public static ReportGrid grid(String id){
    return new ReportGrid(id);
  }

  /**
   * Icon
   * @param id Icon id
   * @return Report icon
   */
  public static Icon icon(String id){
    return new Icon(id);
  }

  /**
   * Image
   * @param id Image id
   * @return Report image
   */
  public static Image image(String id){
    return new Image(id);
  }

  /**
   * Horizontal layout
   * @param id Layout id
   * @return Layout
   */
  public static Layout layout(String id){
    return new Layout(id, LayoutType.HORIZONTAL);
  }

  /**
   * Layout
   * @param id Layout id
   * @param layoutType Layout type
   * @return Layout
   */
  public static Layout layout(String id, LayoutType layoutType){
    return new Layout(id, layoutType);
  }

  /**
   * Text
   * @param id Text id
   * @return Text
   */
  public static Text text(String id){
    return new Text(id);
  }

  /**
   * URL
   * @param id URL id
   * @return URL
   */
  public static URL url(String id){
    return new URL(id);
  }

  /**
   * PageBreak
   * @param id PageBreak id
   * @return PageBreak
   */
  public static PageBreak pageBreak(String id){
    return new PageBreak(id);
  }

  /**
   * PageBreak
   * @param id PageBreak id
   * @param breakType Break type
   * @return PageBreak
   */
  public static PageBreak pageBreak(String id, BreakType breakType){
    return new PageBreak(id, breakType);
  }

  /**
   * Paging
   * @param id Paging id
   * @return Paging
   */
  public static Paging paging(String id){return new Paging(id);}

  /**
   * Paging
   * @param id Paging id
   * @param pagingType Paging type
   * @return Paging
   */
  public static Paging paging(String id, PagingType pagingType){return new Paging(id, pagingType);}

  /**
   * Barcode
   * @param id Barcode id
   * @param type Barcode code type
   * @return Barcode
   */
  public static Barcode barcode(String id, BarcodeType type){return new Barcode(id, type);}

  /**
   * Current date
   * @param id Date id
   * @return Current date
   */
  public static CurrentDate currentDate(String id){return new CurrentDate(id);}
}
