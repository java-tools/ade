package com.almis.ade.api.bean.component;

import com.almis.ade.api.enumerate.PagingType;

/**
 * Paging element class
 * @author dfuentes
 */
public class Paging extends Element<Paging> {

  private PagingType pagingType;

  /**
   * Paging constructor
   * @param identifier paging identifier
   */
  public Paging(String identifier) {
    super(identifier);
    setPagingType(PagingType.X_OF_Y);
  }

  /**
   * Paging constructor
   * @param identifier paging identifier
   * @param pagingType paging type
   */
  public Paging(String identifier, PagingType pagingType) {
    super(identifier);
    setPagingType(pagingType);
  }

  /**
   * Get paging type
   *
   * @return PagingType
   */
  public PagingType getPagingType() {
    return pagingType;
  }

  /**
   * Set paging type
   *
   * @param pagingType paging type
   *
   * @return Paging element
   */
  public Paging setPagingType(PagingType pagingType) {
    this.pagingType = pagingType;
    return this;
  }

}
