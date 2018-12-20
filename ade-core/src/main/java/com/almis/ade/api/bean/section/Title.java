package com.almis.ade.api.bean.section;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.bean.component.Image;
import com.almis.ade.api.bean.component.Text;

/**
 *
 * @author dfuentes
 */
public class Title extends Element<Title> {
  private Image logo;
  private Text text;
  private Text date;

  /**
   *
   * @param identifier
   */
  public Title(String identifier) {
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
  public Title setLogo(Image logo) {
    this.logo = logo;
    return this;
  }

  /**
   *
   * @return
   */
  public Text getTitle() {
    return text;
  }

  /**
   *
   * @param title
   * @return
   */
  public Title setTitle(Text title) {
    this.text = title;
    return this;
  }

  /**
   *
   * @return
   */
  public Text getDate() {
    return date;
  }

  /**
   *
   * @param date
   * @return
   */
  public Title setDate(Text date) {
    this.date = date;
    return this;
  }
}
