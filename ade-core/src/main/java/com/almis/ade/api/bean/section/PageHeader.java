package com.almis.ade.api.bean.section;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.bean.component.Image;
import com.almis.ade.api.bean.component.Line;
import com.almis.ade.api.bean.component.Text;

/**
 *
 * @author dfuentes
 */
public class PageHeader extends Element<PageHeader> {

  private Image logo;
  private Text title;
  private Line line;

  /**
   * PageHeader constructor
   * @param identifier page header identifier
   */
  public PageHeader(String identifier) {
    super(identifier);
  }

  /**
   * Get logo
   * @return Image element
   */
  public Image getLogo() {
    return logo;
  }

  /**
   * Set logo
   * @param logo logo
   * @return PageHeader
   */
  public PageHeader setLogo(Image logo) {
    this.logo = logo;
    return this;
  }

  /**
   * Get page header title
   * @return Text of page header title
   */
  public Text getTitle() {
    return title;
  }

  /**
   * Set title
   * @param title title
   * @return PageHeader element
   */
  public PageHeader setTitle(Text title) {
    this.title = title;
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
   * Set line
   * @param line line
   * @return PageHeader element
   */
  public PageHeader setLine(Line line) {
    this.line = line;
    return this;
  }
}
