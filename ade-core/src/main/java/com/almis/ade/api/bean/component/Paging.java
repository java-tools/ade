package com.almis.ade.api.bean.component;

import com.almis.ade.api.enumerate.PagingType;

/**
 *
 * @author dfuentes
 */
public class Paging extends Element<Paging> {

  private PagingType pagingType;

  /**
   *
   * @param identifier
   */
  public Paging(String identifier) {
    super(identifier);
    setPagingType(PagingType.X_OF_Y);
  }

  /**
   *
   * @param identifier
   * @param pagingType
   */
  public Paging(String identifier, PagingType pagingType) {
    super(identifier);
    setPagingType(pagingType);
  }

  /**
   * Get paging type
   *
   * @return
   */
  public PagingType getPagingType() {
    return pagingType;
  }

  /**
   * Set paging type
   *
   * @param pagingType
   *
   * @return
   */
  public Paging setPagingType(PagingType pagingType) {
    this.pagingType = pagingType;
    return this;
  }

}
