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
   *
   * @param identifier
   */
  public PageHeader(String identifier) {
    super(identifier);
  }

  /**
   *
   * @return
   */
  public Image getLogo() {
    return logo;
  }

  /**
   *
   * @param logo
   * @return
   */
  public PageHeader setLogo(Image logo) {
    this.logo = logo;
    return this;
  }

  /**
   *
   * @return
   */
  public Text getTitle() {
    return title;
  }

  /**
   *
   * @param title
   * @return
   */
  public PageHeader setTitle(Text title) {
    this.title = title;
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
  public PageHeader setLine(Line line) {
    this.line = line;
    return this;
  }
}
