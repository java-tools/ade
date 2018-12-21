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
   * Title constructor
   * @param identifier title identifier
   */
  public Title(String identifier) {
    super(identifier);
  }

  /**
   * Get logo
   * @return Image
   */
  public Image getLogo() {
    return logo;
  }

  /**
   * Set logo
   * @param logo logo
   * @return Title element
   */
  public Title setLogo(Image logo) {
    this.logo = logo;
    return this;
  }

  /**
   * Get title
   * @return Text
   */
  public Text getTitle() {
    return text;
  }

  /**
   * Set title
   * @param title title
   * @return Title element
   */
  public Title setTitle(Text title) {
    this.text = title;
    return this;
  }

  /**
   * Get date
   * @return Text
   */
  public Text getDate() {
    return date;
  }

  /**
   * Set date
   * @param date date
   * @return Title element
   */
  public Title setDate(Text date) {
    this.date = date;
    return this;
  }
}
