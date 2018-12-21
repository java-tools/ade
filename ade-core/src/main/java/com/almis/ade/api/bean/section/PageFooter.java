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
   * PageFooter constructor
   * @param identifier pageFooter identifier
   */
  public PageFooter(String identifier) {
    super(identifier);
  }

  /**
   * Get paging
   * @return Paging element
   */
  public Paging getPaging() {
    return paging;
  }

  /**
   * Set paging
   * @param paging paging
   * @return PageFooter
   */
  public PageFooter setPaging(Paging paging) {
    this.paging = paging;
    return this;
  }

  /**
   * Get page footer variables
   * @return vaiable list
   */
  public List<Element> getVariables() {
    return variables;
  }

  /**
   * Set page footer variables
   * @param variables page footer variable list
   * @return PageFooter
   */
  public PageFooter setVariables(List<Element> variables) {
    this.variables = variables;
    return this;
  }

  /**
   * Get line
   * @return Line element
   */
  public Line getLine() {
    return line;
  }

  /**
   * Set Line
   * @param line line
   * @return PageFooter element
   */
  public PageFooter setLine(Line line) {
    this.line = line;
    return this;
  }
}
