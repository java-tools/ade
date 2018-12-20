package com.almis.ade.api.bean.section;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.bean.component.Line;
import com.almis.ade.api.bean.component.Paging;

import java.util.List;

/**
 *
 * @author dfuentes
 */
public class PageFooter extends Element<PageFooter> {

  private Paging paging;
  private List<Element> variables;
  private Line line;

  /**
   *
   * @param identifier
   */
  public PageFooter(String identifier) {
    super(identifier);
  }

  /**
   *
   * @return
   */
  public Paging getPaging() {
    return paging;
  }

  /**
   *
   * @param paging
   * @return
   */
  public PageFooter setPaging(Paging paging) {
    this.paging = paging;
    return this;
  }

  /**
   *
   * @return
   */
  public List<Element> getVariables() {
    return variables;
  }

  /**
   *
   * @param variables
   * @return
   */
  public PageFooter setVariables(List<Element> variables) {
    this.variables = variables;
    return this;
  }

  /**
   *
   * @return
   */
  public Line getLine() {
    return line;
  }

  /**
   *
   * @param line
   * @return
   */
  public PageFooter setLine(Line line) {
    this.line = line;
    return this;
  }
}
